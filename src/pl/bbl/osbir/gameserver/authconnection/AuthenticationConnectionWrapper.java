package pl.bbl.osbir.gameserver.authconnection;

import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;
import pl.bbl.osbir.gameserver.authconnection.instance.AuthenticationServerConnection;
import pl.bbl.osbir.gameserver.authconnection.properties.AuthenticationConnectionProperties;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class AuthenticationConnectionWrapper {
    private AuthenticationServerConnection authenticationServerConnection;
    private Thread authenticationConnectionThread;

    public AuthenticationConnectionWrapper(SegmentsCommunicationDirector segmentsCommunicationDirector){
        authenticationServerConnection = new AuthenticationServerConnection(AuthenticationConnectionProperties.AUTHENTICATION_HOST, 
                AuthenticationConnectionProperties.AUTHENTICATION_PORT, segmentsCommunicationDirector);
        authenticationConnectionThread = new Thread(authenticationServerConnection);
    }

    public void establishConnection() {
        authenticationConnectionThread.start();
        ServerLogger.log("Establishing connection to authentication server...");
    }

    public void requestGameServerAuthentication(){
        authenticationServerConnection.verifyServer();
    }
}
