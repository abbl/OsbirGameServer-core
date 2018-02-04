package pl.bbl.osbir.gameserver.server.player;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;

public class Player extends AbstractUser {
    private String userIdAcquiredFromAuthServer;
    private String usernameAcquiredFromAuthServer;

    public Player(String id, Channel channel) {
        super(id, channel);
    }

    public void setUserIdAcquiredFromAuthServer(String userIdAcquiredFromAuthServer){
        this.userIdAcquiredFromAuthServer = userIdAcquiredFromAuthServer;
    }

    public String getUserIdAcquiredFromAuthServer(){
        return userIdAcquiredFromAuthServer;
    }

    public String getUsernameAcquiredFromAuthServer() {
        return usernameAcquiredFromAuthServer;
    }

    public void setUsernameAcquiredFromAuthServer(String usernameAcquiredFromAuthServer) {
        this.usernameAcquiredFromAuthServer = usernameAcquiredFromAuthServer;
    }
}
