package pl.bbl.osbir.gameserver.server;

import pl.bbl.osbir.gameserver.server.instance.GameServer;
import pl.bbl.osbir.gameserver.server.player.Player;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class GameServerWrapper {
    private GameServer gameServer;
    private Thread gameServerThread;
    private static final Object lock = new Object();

    public GameServerWrapper(){
        gameServer = new GameServer(GameServerProperties.GAMESERVER_PORT, Player.class);
        gameServerThread = new Thread(gameServer);
    }

    public void startGameServer(){
        new Thread(() -> {
            synchronized (lock){
                while(!gameServer.isAuthenticated()){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                gameServerThread.start();
                ServerLogger.log("Server started.");
            }
        }).start();
        ServerLogger.log("Server will be started after its authentication.");
    }

    public void authenticateServer(boolean result){
        synchronized (lock){
            if(result) {
                gameServer.setAuthenticated(true);
                lock.notifyAll();
            }
            else
                ServerLogger.log("AuthenticationServer rejected authentication request, probably server key is wrong.");
        }
    }

    public void receiveVerificationResult(String userId, boolean result){
        Player player = gameServer.getPlayerById(userId);

        if(player != null && result)
            player.setAuthenticated(true);
        else{
            gameServer.disconnectUser(userId);
            ServerLogger.log("A user with fake key was forced to disconnect");
        }
    }
}
