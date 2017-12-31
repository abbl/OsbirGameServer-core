package pl.bbl.osbir.gameserver.features.route.user.information.receiver;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.gameserver.features.route.user.information.packets.UserInformationPackets;
import pl.bbl.osbir.gameserver.server.instance.GameServer;
import pl.bbl.osbir.gameserver.server.player.Player;

public class UserInformationReceiver extends PacketReceiver {
    private GameServer gameServer;

    public UserInformationReceiver(String receiverType, Player player, GameServer gameServer) {
        super(receiverType, player);
        this.gameServer = gameServer;
    }

    @Override
    public boolean receive(Packet packet) {
        switch(packet.packetPurpose){
            case "USER_ID_TO_VERIFY":
                passIdToVerification((String) packet.getData("userId"));
                return true;
        }
        return false;
    }

    public void sendVerificationResult(boolean result){
        getPlayer().setAuthenticated(result);
        sendPacket(UserInformationPackets.createVerificationResult(result));
    }

    private void passIdToVerification(String userIdToVerify){
        getPlayer().setUserIdAcquiredFromAuthServer(userIdToVerify);
        gameServer.requestUserVerification(userIdToVerify);
    }

    private Player getPlayer(){
        return (Player) abstractUser;
    }
}
