package pl.bbl.osbir.gameserver.server.receivers;

import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.osbir.gameserver.features.route.user.information.receiver.UserInformationReceiver;
import pl.bbl.osbir.gameserver.server.instance.GameServer;
import pl.bbl.osbir.gameserver.server.player.Player;

public class GameServerReceivers {
    public static final String USER_INFORMATION = "INFORMATION_PACKETS";

    public static void addReceivers(Player player, GameServer gameServer){
        PacketHandler packetHandler = player.retrievePacketHandlerFromPipeline();
        packetHandler.addReceiver(new UserInformationReceiver(USER_INFORMATION, player, gameServer));
    }
}
