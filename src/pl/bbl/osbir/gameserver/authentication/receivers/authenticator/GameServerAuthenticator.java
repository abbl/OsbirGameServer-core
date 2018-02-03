package pl.bbl.osbir.gameserver.authentication.receivers.authenticator;

import pl.bbl.network.client.ClientConnection;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handler.PacketReceiver;
import pl.bbl.osbir.gameserver.authentication.receivers.information.packets.InformationExchangerPackets;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class GameServerAuthenticator extends PacketReceiver{
    public GameServerAuthenticator(){
        super("GameServerAuthenticator");
    }

    @Override
    public void receivePacket(Packet packet, ClientConnection clientConnection) {
        switch (packet.getPacketType()){
            case "GAMESERVER_AUTHENTICATION_RESULT":
                authenticateGameServer((boolean) packet.getData("result"), clientConnection);
        }
    }

    private void authenticateGameServer(boolean result, ClientConnection clientConnection) {
        if(result){
            GameServerProperties.isAuthenticated = true;
            clientConnection.sendPacket(InformationExchangerPackets.createGameServerDescPacket());
            ServerLogger.log("GameServer has been authenticated.");
            ServerLogger.log("GameServer information has been sent.");
        } else
            ServerLogger.log("AuthenticationServer rejected AuthenticationKey.");
    }
}
