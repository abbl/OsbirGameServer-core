package pl.bbl.osbir.gameserver.server.instance;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.osbir.gameserver.server.player.Player;

public class GameServer extends AbstractServer {
    private boolean isAuthenticated;

    public GameServer(int port, Class className) {
        super(port, className);
        this.isAuthenticated = false;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        return super.addHandlersToChannel(pipeline);
    }

    public void setAuthenticated(boolean isAuthenticated){
        this.isAuthenticated = isAuthenticated;
    }

    public boolean isAuthenticated(){
        return isAuthenticated;
    }

    public void disconnectUser(String userId){
        userHive.removeUser(userId);
    }

    public Player getPlayerById(String userId){
        return (Player) userHive.getUserById(userId);
    }
}
