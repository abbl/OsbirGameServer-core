package pl.bbl.osbir.gameserver.features.route.authenticationserver.information.receiver;

import pl.bbl.network.client.AbstractClient;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.gameserver.features.route.authenticationserver.information.packets.InformationPackets;

public class InformationReceiver extends PacketReceiver {

    public InformationReceiver(String receiverType, AbstractClient abstractClient) {
        super(receiverType, abstractClient);
    }

    @Override
    public boolean receive(Packet packet) {
        switch(packet.packetPurpose){
            case "USER_AUTHENTICATION_RESULT":
                return true;
        }
        return false;
    }

    public void requestUserVerification(String userId){
        sendPacket(InformationPackets.createUserVerificationPacket(userId));
    }
}
