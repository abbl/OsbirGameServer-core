package pl.bbl.osbir.gameserver.authserver.connection;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.client.AbstractClient;

public class AuthenticationServerConnection extends AbstractClient {
    private boolean isAuthenticated;

    public AuthenticationServerConnection(String host, int port) {
        super(host, port);
        this.isAuthenticated = false;
    }

    @Override
    protected void addHandlersToChannel(ChannelPipeline pipeline) {
        super.addHandlersToChannel(pipeline);
    }

    public void verifyServer(){}

    public void setAuthenticated(boolean isAuthenticated){
        this.isAuthenticated = isAuthenticated;
    }

    public boolean isAuthenticated(){
        return isAuthenticated;
    }
}
