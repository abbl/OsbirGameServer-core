package pl.bbl.osbir.gameserver.features.authentication.packets;

import pl.bbl.network.packet.Packet;

public class GameServerAuthenticationPacket {
    public static Packet createPacket(String gameServerKey){
        Packet packet = new Packet("AUTHENTICATION_PACKETS", "AUTHENTICATION_START");
        packet.addData("authenticationKey", gameServerKey);
        return packet;
    }
}
