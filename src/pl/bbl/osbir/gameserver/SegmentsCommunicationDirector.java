package pl.bbl.osbir.gameserver;

import pl.bbl.osbir.gameserver.authentication.connection.AuthenticationConnection;
import pl.bbl.osbir.gameserver.server.GameServer;

public class SegmentsCommunicationDirector {
    private AuthenticationConnection authenticationConnection;
    private GameServer gameServer;

    public SegmentsCommunicationDirector(){
        this.authenticationConnection = new AuthenticationConnection(this);
        this.gameServer = new GameServer(this);
    }

    public void establishConnectionToAuthenticationServer(){
        authenticationConnection.start();
    }

    public void requestGameServerAuthentication(){
        authenticationConnection.requestAuthentication();
    }

    public void startGameServer(){
        gameServer.start();
    }

    public void requestPlayerAuthentication(String userKey, String username) {
        authenticationConnection.requestPlayerAuthentication(userKey, username);
    }

    public void passPlayerVerificationResult(String userKey, String username, boolean result) {
        gameServer.receivePlayerAuthentication(userKey, username, result);
    }
}
