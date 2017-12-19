package pl.bbl.osbir.gameserver.features.information.packets;

import pl.bbl.network.packet.Packet;

public abstract class VerificationPacket {
    public static Packet createPacket(String userKey){
        return new Packet("INFORMATION_EXCHANGE", "VERIFY_USER");
    }
}
