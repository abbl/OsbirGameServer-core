package pl.bbl.osbir.gameserver.features.route.authenticationserver.information.packets;

import pl.bbl.network.packet.Packet;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;

public abstract class InformationPackets {
    private static String packetType = "INFORMATION_PACKETS";

    public static Packet createUserVerificationPacket(String userKey){
        return new Packet(packetType, "VERIFY_USER").addData("userKey", userKey);
    }

    public static Packet createServerInformationPacket(){
        return new Packet(packetType, "UPDATE_SERVER_INFORMATION").addData("name", GameServerProperties.SERVER_NAME)
                .addData("host", GameServerProperties.SERVER_HOST).addData("port", GameServerProperties.GAMESERVER_PORT);
    }
}
