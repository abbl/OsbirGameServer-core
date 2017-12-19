package pl.bbl.osbir.gameserver.authserver.connection;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.client.AbstractClient;
import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.osbir.gameserver.features.authentication.receiver.GameServerAuthenticationReceiver;
import pl.bbl.osbir.gameserver.server.GameServer;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;

public class AuthenticationServerConnection extends AbstractClient {
    private GameServer gameServer;
    private GameServerAuthenticationReceiver gameServerAuthenticationReceiver;

    public AuthenticationServerConnection(String host, int port, GameServer gameServer) {
        super(host, port);
        this.gameServer = gameServer;
        gameServerAuthenticationReceiver = new GameServerAuthenticationReceiver("AUTHENTICATION_PACKETS", gameServer, this);
    }

    @Override
    protected void addHandlersToChannel(ChannelPipeline pipeline) {
        super.addHandlersToChannel(pipeline);
        PacketHandler packetHandler = new PacketHandler();
        pipeline.addLast(packetHandler);
        addReceiversToHandler(packetHandler);
    }

    private void addReceiversToHandler(PacketHandler packetHandler){
        packetHandler.addReceiver(gameServerAuthenticationReceiver);
    }

    public void verifyServer(){
        if(gameServerAuthenticationReceiver != null)
            gameServerAuthenticationReceiver.requestAuthentication(GameServerProperties.AUTHENTICATION_KEY);
    }
}
