package pl.bbl.osbir.gameserver.authentication.receivers.authenticator.packets;

import pl.bbl.network.packet.Packet;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;

public class AuthenticationPackets {
    public static Packet createAuthenticationRequestPacket(){
        return new Packet("START_GAMESERVER_AUTHENTICATION").addData("authenticationKey", GameServerProperties.AUTHENTICATION_KEY);
    }
}
