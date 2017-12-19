package pl.bbl.osbir.gameserver.tools;

import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;

public class ServerLogger {
    public static void log(String message){
        System.out.println("[" + GameServerProperties.SERVER_NAME + "]" + " " + message);
    }
}
