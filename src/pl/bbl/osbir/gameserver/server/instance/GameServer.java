package pl.bbl.osbir.gameserver.server.instance;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.osbir.gameserver.server.GameServerWrapper;
import pl.bbl.osbir.gameserver.server.player.Player;

public class GameServer extends AbstractServer {
    private boolean isAuthenticated;
    private GameServerWrapper gameServerWrapper;

    public GameServer(int port, Class className, GameServerWrapper gameServerWrapper) {
        super(port, className);
        this.gameServerWrapper = gameServerWrapper;
        this.isAuthenticated = false;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        Player player = (Player) super.addHandlersToChannel(pipeline);
        gameServerWrapper.requestUserVerification(player.getId());

        return player;
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
