package pl.bbl.osbir.gameserver.features.route.user.information.packets;

import pl.bbl.network.packet.Packet;
import pl.bbl.osbir.gameserver.server.receivers.GameServerReceivers;

public abstract class UserInformationPackets {
    public static Packet createVerificationResult(boolean result){
        return new Packet(GameServerReceivers.USER_INFORMATION, "VERIFICATION_RESULT").addData("result", result);
    }
}
