package pl.bbl.osbir.gameserver.authconnection;

import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;
import pl.bbl.osbir.gameserver.authconnection.instance.AuthenticationServerConnection;
import pl.bbl.osbir.gameserver.authconnection.properties.AuthenticationConnectionProperties;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class AuthenticationConnectionWrapper {
    private AuthenticationServerConnection authenticationServerConnection;
    private SegmentsCommunicationDirector segmentsCommunicationDirector;
    private Thread authenticationConnectionThread;

    public AuthenticationConnectionWrapper(SegmentsCommunicationDirector segmentsCommunicationDirector){
        this.segmentsCommunicationDirector = segmentsCommunicationDirector;
        initializeConnectionObjects();
    }

    private void initializeConnectionObjects(){
        authenticationServerConnection = new AuthenticationServerConnection(AuthenticationConnectionProperties.AUTHENTICATION_HOST,
                AuthenticationConnectionProperties.AUTHENTICATION_PORT, this);
        authenticationConnectionThread = new Thread(authenticationServerConnection);
    }

    public void establishConnection() {
        authenticationConnectionThread.start();
        ServerLogger.log("Establishing connection to authentication server...");
    }

    public void requestGameServerAuthentication(){
        authenticationServerConnection.verifyServer();
    }

    public void passGameServerAuthenticationResult(boolean result){
        segmentsCommunicationDirector.passServerAuthenticationResult(result);
    }

    public void requestUserVerification(String userId) {
        authenticationServerConnection.verifyUser(userId);
    }

    public void passUserVerificationResult(String userId, boolean result){
        segmentsCommunicationDirector.passUserVerificationResult(userId, result);
    }
}
