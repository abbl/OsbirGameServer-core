package pl.bbl.osbir.gameserver.features.information.packets;

import pl.bbl.network.packet.Packet;

public class PlayerInformationExchangerPackets {
    public static Packet createVerificationResultPacket(boolean result){
        return new Packet("PLAYER_VERIFICATION_RESULT").addData("result", result);
    }
}
