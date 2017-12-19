package pl.bbl.osbir.gameserver.server.player;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;

public class Player extends AbstractUser {
    public Player(String id, Channel channel) {
        super(id, channel);
    }
}
