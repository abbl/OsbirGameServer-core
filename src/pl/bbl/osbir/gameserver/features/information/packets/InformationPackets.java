package pl.bbl.osbir.gameserver.features.information.packets;

import pl.bbl.network.packet.Packet;

public abstract class InformationPackets {
    public static Packet createUserVerificationPacket(String userKey){
        return new Packet("INFORMATION_EXCHANGE", "VERIFY_USER").addData("userKey", userKey);
    }
}
