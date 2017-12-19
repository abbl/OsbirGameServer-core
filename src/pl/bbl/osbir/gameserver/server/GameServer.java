package pl.bbl.osbir.gameserver.server;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;

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
}
