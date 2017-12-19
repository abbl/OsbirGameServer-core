package pl.bbl.osbir.gameserver;

import pl.bbl.osbir.gameserver.authserver.connection.AuthenticationServerConnection;
import pl.bbl.osbir.gameserver.authserver.connection.properties.AuthenticationConnectionProperties;
import pl.bbl.osbir.gameserver.server.GameServer;
import pl.bbl.osbir.gameserver.server.player.Player;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;

public class GameServerStarter {
    public static void main(String args[]){
        Thread thread = new Thread(new GameServer(GameServerProperties.GAMESERVER_PORT, Player.class));
        thread.start();
        Thread thread1 = new Thread(new AuthenticationServerConnection(AuthenticationConnectionProperties.AUTHENTICATION_HOST, AuthenticationConnectionProperties.AUTHENTICATION_PORT));
        System.out.println("[Info]Server started");
        thread1.start();
    }
}
