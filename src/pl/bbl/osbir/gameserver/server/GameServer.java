package pl.bbl.osbir.gameserver.server;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;

public class GameServer extends AbstractServer {
    public GameServer(int port, Class className) {
        super(port, className);
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        return super.addHandlersToChannel(pipeline);
    }
}
