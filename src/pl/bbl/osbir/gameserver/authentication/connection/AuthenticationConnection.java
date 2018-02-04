package pl.bbl.osbir.gameserver.authentication.connection;

import pl.bbl.network.client.Client;
import pl.bbl.network.server.handler.PacketDistributor;
import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;
import pl.bbl.osbir.gameserver.authentication.receivers.authenticator.GameServerAuthenticator;
import pl.bbl.osbir.gameserver.authentication.receivers.authenticator.packets.AuthenticationPackets;
import pl.bbl.osbir.gameserver.authentication.receivers.information.InformationExchangerReceiver;
import pl.bbl.osbir.gameserver.authentication.receivers.information.packets.InformationExchangerPackets;
import pl.bbl.osbir.gameserver.server.properties.GameServerProperties;
import pl.bbl.osbir.gameserver.tools.ServerLogger;

public class AuthenticationConnection {
    private SegmentsCommunicationDirector segmentsCommunicationDirector;
    private Client client;
    private Thread clientThread;
    private PacketDistributor packetDistributor;

    public AuthenticationConnection(SegmentsCommunicationDirector segmentsCommunicationDirector){
        this.packetDistributor = new PacketDistributor();
        this.client = new Client(GameServerProperties.AUTHENTICATION_HOST, GameServerProperties.AUTHENTICATION_PORT, packetDistributor);
        this.clientThread = new Thread(client);
        this.segmentsCommunicationDirector = segmentsCommunicationDirector;
        addPacketReceivers();
    }

    private void addPacketReceivers(){
        packetDistributor.registerPacketReceiver(new GameServerAuthenticator());
        packetDistributor.registerPacketReceiver(new InformationExchangerReceiver(this));
    }

    public void start(){
        ServerLogger.log("Connecting to AuthenticationServer.");
        clientThread.start();
        ServerLogger.log("Probably connected.");
    }

    public void close(){
        clientThread.interrupt();
    }

    public void requestAuthentication(){
        ServerLogger.log("Requested gameserver authentication.");
        client.write(AuthenticationPackets.createAuthenticationRequestPacket());
    }

    public void requestPlayerAuthentication(String userKey, String username){
        client.write(InformationExchangerPackets.createPlayerVerificationPacket(userKey, username));
    }

    public void passVerificationResult(String userKey, String username, boolean result) {
        segmentsCommunicationDirector.passPlayerVerificationResult(userKey, username, result);
    }
}
