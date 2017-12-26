package pl.bbl.osbir.gameserver.authserver.connection.instance;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.client.AbstractClient;
import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;
import pl.bbl.osbir.gameserver.features.authentication.receiver.GameServerAuthenticationReceiver;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;

public class AuthenticationServerConnection extends AbstractClient {
    private SegmentsCommunicationDirector segmentsCommunicationDirector;
    private GameServerAuthenticationReceiver gameServerAuthenticationReceiver;

    public AuthenticationServerConnection(String host, int port, SegmentsCommunicationDirector segmentsCommunicationDirector) {
        super(host, port);
        this.segmentsCommunicationDirector = segmentsCommunicationDirector;
        gameServerAuthenticationReceiver = new GameServerAuthenticationReceiver("AUTHENTICATION_PACKETS", this, segmentsCommunicationDirector);
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
