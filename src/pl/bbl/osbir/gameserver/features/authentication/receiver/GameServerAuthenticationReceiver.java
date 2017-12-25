package pl.bbl.osbir.gameserver.features.authentication.receiver;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.gameserver.authserver.connection.instance.AuthenticationServerConnection;
import pl.bbl.osbir.gameserver.features.authentication.GameServerAuthenticator;
import pl.bbl.osbir.gameserver.features.authentication.packets.AuthenticationPackets;
import pl.bbl.osbir.gameserver.server.instance.GameServer;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class GameServerAuthenticationReceiver extends PacketReceiver {
    private GameServer gameServer;
    private AuthenticationServerConnection connection;

    public GameServerAuthenticationReceiver(String receiverType, GameServer gameServer, AuthenticationServerConnection connection) {
        super(receiverType);
        this.gameServer = gameServer;
        this.connection = connection;
    }

    @Override
    public boolean receive(Packet packet) {
        switch (packet.packetPurpose){
            case "AUTHENTICATION_RESULT":
                GameServerAuthenticator.authenticateGameServer(gameServer, packet);
                return true;
        }
        return false;
    }

    public void requestAuthentication(String gameServerAuthenticationKey){
        ServerLogger.log("Server authentication has been requested.");
        connection.write(AuthenticationPackets.createGameServerAuthenticationPacket(gameServerAuthenticationKey));
    }
}
