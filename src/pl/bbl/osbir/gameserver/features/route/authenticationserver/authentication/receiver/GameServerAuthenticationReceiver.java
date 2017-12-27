package pl.bbl.osbir.gameserver.features.route.authenticationserver.authentication.receiver;

import pl.bbl.network.client.AbstractClient;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;
import pl.bbl.osbir.gameserver.features.route.authenticationserver.authentication.packets.AuthenticationPackets;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class GameServerAuthenticationReceiver extends PacketReceiver {
    private SegmentsCommunicationDirector segmentsCommunicationDirector;

    public GameServerAuthenticationReceiver(String receiverType, AbstractClient abstractClient, SegmentsCommunicationDirector segmentsCommunicationDirector) {
        super(receiverType, abstractClient);
        this.segmentsCommunicationDirector = segmentsCommunicationDirector;
    }

    @Override
    public boolean receive(Packet packet) {
        switch (packet.packetPurpose){
            case "AUTHENTICATION_RESULT":
                segmentsCommunicationDirector.passServerAuthenticationResult((boolean)packet.getData("result"));
                return true;
        }
        return false;
    }

    public void requestAuthentication(String gameServerAuthenticationKey){
        sendPacket(AuthenticationPackets.createGameServerAuthenticationPacket(gameServerAuthenticationKey));
        ServerLogger.log("Server authentication has been requested.");
    }
}
