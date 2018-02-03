package pl.bbl.osbir.gameserver;

import pl.bbl.osbir.gameserver.authentication.connection.AuthenticationConnection;

public class SegmentsCommunicationDirector {
    private AuthenticationConnection authenticationConnection;

    public SegmentsCommunicationDirector(){
        this.authenticationConnection = new AuthenticationConnection();
    }

    public void establishConnectionToAuthenticationServer(){
        authenticationConnection.start();
    }

    public void requestGameServerAuthentication(){
        authenticationConnection.requestAuthentication();
    }
}
