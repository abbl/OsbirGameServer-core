package pl.bbl.osbir.gameserver.features.information;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handler.PacketReceiver;
import pl.bbl.osbir.gameserver.server.GameServer;
import pl.bbl.osbir.gameserver.server.player.Player;

public class PlayerInformationExchanger extends PacketReceiver{
    private static GameServer gameServer;

    public PlayerInformationExchanger(GameServer gameServer) {
        super("PlayerInformationExchanger");
        PlayerInformationExchanger.gameServer = gameServer;
    }

    @Override
    public void receivePacket(Packet packet, AbstractUser abstractUser) {
        switch (packet.getPacketType()){
            case "START_PLAYER_AUTHENTICATION":
                authenticatePlayer(packet, (Player) abstractUser);
        }
    }

    private void authenticatePlayer(Packet packet, Player player) {
        player.setUserIdAcquiredFromAuthServer((String) packet.getData("userKey"));
        player.setUsernameAcquiredFromAuthServer((String) packet.getData("username"));

        gameServer.requestPlayerVerification(player.getUserIdAcquiredFromAuthServer(), player.getUsernameAcquiredFromAuthServer());
    }
}
