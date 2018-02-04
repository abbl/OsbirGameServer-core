package pl.bbl.osbir.gameserver.server;

import pl.bbl.network.server.Server;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handler.PacketDistributor;
import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;
import pl.bbl.osbir.gameserver.features.information.PlayerInformationExchanger;
import pl.bbl.osbir.gameserver.features.information.packets.PlayerInformationExchangerPackets;
import pl.bbl.osbir.gameserver.server.player.Player;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class GameServer {
    private Server server;
    private Thread serverThread;
    private PacketDistributor packetDistributor;
    private SegmentsCommunicationDirector segmentsCommunicationDirector;

    public GameServer(SegmentsCommunicationDirector segmentsCommunicationDirector){
        this.packetDistributor = new PacketDistributor();
        this.server = new Server(GameServerProperties.GAMESERVER_PORT, Player.class, packetDistributor);
        this.serverThread = new Thread(server);
        this.segmentsCommunicationDirector = segmentsCommunicationDirector;
        registerPacketReceivers();
    }

    private void registerPacketReceivers() {
        packetDistributor.registerPacketReceiver(new PlayerInformationExchanger(this));
    }

    public void start(){
        ServerLogger.log("GameServer has been started.");
        serverThread.start();
    }

    public void requestPlayerVerification(String userKey, String username){
        segmentsCommunicationDirector.requestPlayerAuthentication(userKey, username);
    }

    public void receivePlayerAuthentication(String userKey, String username, boolean result){
        for(AbstractUser abstractUser : server.getUsers()){
            Player player = (Player) abstractUser;
            if(player.getUserIdAcquiredFromAuthServer().equals(userKey) && player.getUsernameAcquiredFromAuthServer().equals(username)){
                ServerLogger.log(username + " has been authenticated.");
                player.setAuthenticated(result);
                player.sendPacket(PlayerInformationExchangerPackets.createVerificationResultPacket(result));
                break;
            }
        }
    }
}
