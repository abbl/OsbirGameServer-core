package pl.bbl.osbir.gameserver;

import pl.bbl.osbir.gameserver.authconnection.AuthenticationConnectionWrapper;
import pl.bbl.osbir.gameserver.server.GameServerWrapper;

public class SegmentsCommunicationDirector {
    private GameServerWrapper gameServerWrapper;
    private AuthenticationConnectionWrapper authenticationConnectionWrapper;

    public SegmentsCommunicationDirector(){
        this.gameServerWrapper = new GameServerWrapper();
        this.authenticationConnectionWrapper = new AuthenticationConnectionWrapper(this);
    }

    public void connectToAuthenticationServer(){
        authenticationConnectionWrapper.establishConnection();
    }

    public void startGameServer(){
        gameServerWrapper.startGameServer();
    }

    public void requestUserVerification(){

    }

    public void passUserVerificationResult(String userId, boolean result){
        gameServerWrapper.receiveVerificationResult(userId, result);
    }

    public void requestGameServerAuthentication(){
        authenticationConnectionWrapper.requestGameServerAuthentication();
    }

    public void passServerAuthenticationResult(boolean result){
        gameServerWrapper.authenticateServer(result);
    }
}
