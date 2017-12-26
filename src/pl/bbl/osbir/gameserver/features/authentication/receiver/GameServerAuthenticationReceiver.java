package pl.bbl.osbir.gameserver.features.authentication.receiver;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;
import pl.bbl.osbir.gameserver.authserver.connection.instance.AuthenticationServerConnection;
import pl.bbl.osbir.gameserver.features.authentication.packets.AuthenticationPackets;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class GameServerAuthenticationReceiver extends PacketReceiver {
    private SegmentsCommunicationDirector segmentsCommunicationDirector;
    private AuthenticationServerConnection connection;

    public GameServerAuthenticationReceiver(String receiverType, AuthenticationServerConnection connection, SegmentsCommunicationDirector segmentsCommunicationDirector) {
        super(receiverType);
        this.connection = connection;
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
        ServerLogger.log("Server authentication has been requested.");
        connection.write(AuthenticationPackets.createGameServerAuthenticationPacket(gameServerAuthenticationKey));
    }
}
