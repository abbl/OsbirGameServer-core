package pl.bbl.osbir.gameserver.server.player;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;

public class Player extends AbstractUser {
    private String userIdAcquiredFromAuthServer;

    public Player(String id, Channel channel) {
        super(id, channel);
    }

    public void setUserIdAcquiredFromAuthServer(String userIdAcquiredFromAuthServer){
        this.userIdAcquiredFromAuthServer = userIdAcquiredFromAuthServer;
    }

    public boolean isUserIdAcquiredFromAuthServerIsEqual(String otherUserId) {
        return userIdAcquiredFromAuthServer.equals(otherUserId);
    }
}
