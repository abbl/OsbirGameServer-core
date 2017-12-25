package pl.bbl.osbir.gameserver;

import pl.bbl.osbir.gameserver.authserver.connection.instance.AuthenticationServerConnection;
import pl.bbl.osbir.gameserver.server.instance.GameServer;

public class SegmentsCommunicationDirector {
    private GameServer gameServer;
    private AuthenticationServerConnection authenticationServerConnection;

    public SegmentsCommunicationDirector(GameServer gameServer, AuthenticationServerConnection authenticationServerConnection){
        this.gameServer = gameServer;
        this.authenticationServerConnection = authenticationServerConnection;
    }

    public void requestUserVerification(){

    }

    public void passUserVerificationResult(){

    }
}
