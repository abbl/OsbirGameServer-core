package pl.bbl.osbir.gameserver.features.authentication;

import pl.bbl.network.packet.Packet;
import pl.bbl.osbir.gameserver.server.instance.GameServer;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class GameServerAuthenticator {
    public static void authenticateGameServer(GameServer gameServer, Packet result){
        gameServer.setAuthenticated((boolean)result.getData("result"));
        if(gameServer.isAuthenticated())
            ServerLogger.log("Server has been authenticated");
        else
            ServerLogger.log("Server hasn't been authenticated");
    }
}
