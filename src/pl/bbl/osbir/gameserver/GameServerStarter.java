package pl.bbl.osbir.gameserver;

import pl.bbl.osbir.gameserver.authserver.connection.AuthenticationServerConnection;
import pl.bbl.osbir.gameserver.authserver.connection.properties.AuthenticationConnectionProperties;
import pl.bbl.osbir.gameserver.server.GameServer;
import pl.bbl.osbir.gameserver.server.player.Player;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class GameServerStarter {
    public static void main(String args[]){
        GameServer gameServer = new GameServer(GameServerProperties.GAMESERVER_PORT, Player.class);
        AuthenticationServerConnection authenticationServerConnection = new AuthenticationServerConnection(AuthenticationConnectionProperties.AUTHENTICATION_HOST,
                AuthenticationConnectionProperties.AUTHENTICATION_PORT, gameServer);
        new Thread(authenticationServerConnection).start();
        authenticationServerConnection.verifyServer();
        while(!gameServer.isAuthenticated());
        ServerLogger.log("Server started.");
        new Thread(gameServer).start();
    }
}
