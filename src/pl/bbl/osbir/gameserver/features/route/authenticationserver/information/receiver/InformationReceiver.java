package pl.bbl.osbir.gameserver.features.route.authenticationserver.information.receiver;

import pl.bbl.network.client.AbstractClient;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.gameserver.authconnection.AuthenticationConnectionWrapper;
import pl.bbl.osbir.gameserver.authconnection.instance.AuthenticationServerConnection;
import pl.bbl.osbir.gameserver.features.route.authenticationserver.information.packets.InformationPackets;

public class InformationReceiver extends PacketReceiver {
    public InformationReceiver(String receiverType, AbstractClient abstractClient) {
        super(receiverType, abstractClient);
    }

    @Override
    public boolean receive(Packet packet) {
        switch(packet.packetPurpose){
            case "USER_AUTHENTICATION_RESULT":
                getAuthenticationServerConnection().passUserVerificationResult((String)packet.getData("userKey"),
                        (boolean)packet.getData("result"));
                return true;
        }
        return false;
    }

    public void requestUserVerification(String userId){
        sendPacket(InformationPackets.createUserVerificationPacket(userId));
    }

    public void updateServerInformation(){
        sendPacket(InformationPackets.createServerInformationPacket());
    }

    private AuthenticationServerConnection getAuthenticationServerConnection(){
        return (AuthenticationServerConnection) abstractClient;
    }
}
