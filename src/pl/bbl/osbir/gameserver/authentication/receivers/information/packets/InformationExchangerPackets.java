package pl.bbl.osbir.gameserver.authentication.receivers.information.packets;

import pl.bbl.network.packet.Packet;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;

public class InformationExchangerPackets {
    public static Packet createGameServerDescPacket(){
        return new Packet("UPDATE_GAMESERVER_INFORMATION").addData("name", GameServerProperties.GAMESERVER_NAME)
                .addData("host", GameServerProperties.GAMESERVER_HOST)
                .addData("port", GameServerProperties.GAMESERVER_PORT);
    }
}
