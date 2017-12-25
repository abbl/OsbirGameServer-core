package pl.bbl.osbir.gameserver.features.authentication.packets;

import pl.bbl.network.packet.Packet;

public class AuthenticationPackets {
    public static Packet createGameServerAuthenticationPacket(String gameServerKey){
        return new Packet("AUTHENTICATION_PACKETS", "AUTHENTICATION_START").addData("authenticationKey", gameServerKey);
    }
}
