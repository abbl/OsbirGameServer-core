package pl.bbl.osbir.gameserver.authconnection.instance;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.client.AbstractClient;
import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;
import pl.bbl.osbir.gameserver.authconnection.AuthenticationConnectionWrapper;
import pl.bbl.osbir.gameserver.features.route.authenticationserver.authentication.receiver.GameServerAuthenticationReceiver;
import pl.bbl.osbir.gameserver.features.route.authenticationserver.information.receiver.InformationReceiver;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;

public class AuthenticationServerConnection extends AbstractClient {
    private AuthenticationConnectionWrapper authenticationConnectionWrapper;
    private GameServerAuthenticationReceiver gameServerAuthenticationReceiver;
    private InformationReceiver informationReceiver;

    public AuthenticationServerConnection(String host, int port, AuthenticationConnectionWrapper authenticationConnectionWrapper) {
        super(host, port);
        this.authenticationConnectionWrapper = authenticationConnectionWrapper;
        initializeReceivers();
    }

    private void initializeReceivers(){
        gameServerAuthenticationReceiver = new GameServerAuthenticationReceiver("AUTHENTICATION_PACKETS", this);
        informationReceiver = new InformationReceiver("INFORMATION_PACKETS", this);
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
        packetHandler.addReceiver(informationReceiver);
    }

    /*
     * Receivers methods.
     */

    public void verifyServer(){
        gameServerAuthenticationReceiver.requestAuthentication(GameServerProperties.AUTHENTICATION_KEY);
    }

    public void passGameServerAuthenticationResult(boolean result){
        authenticationConnectionWrapper.passGameServerAuthenticationResult(result);
    }

    public void verifyUser(String userId) {
        informationReceiver.requestUserVerification(userId);
    }

    public void passUserVerificationResult(String userId, boolean result){
        authenticationConnectionWrapper.passUserVerificationResult(userId, result);
    }
}
