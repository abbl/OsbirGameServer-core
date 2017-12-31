package pl.bbl.osbir.gameserver.server.instance;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.osbir.gameserver.features.route.user.information.receiver.UserInformationReceiver;
import pl.bbl.osbir.gameserver.server.GameServerWrapper;
import pl.bbl.osbir.gameserver.server.player.Player;
import pl.bbl.osbir.gameserver.server.receivers.GameServerReceivers;

public class GameServer extends AbstractServer {
    private boolean isAuthenticated;
    private GameServerWrapper gameServerWrapper;

    public GameServer(int port, Class className, GameServerWrapper gameServerWrapper) {
        super(port, className);
        this.gameServerWrapper = gameServerWrapper;
        this.isAuthenticated = false;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        Player player = (Player) super.addHandlersToChannel(pipeline);
        GameServerReceivers.addReceivers(player, this);
        return player;
    }

    public void requestUserVerification(String userIdToVerify){
        gameServerWrapper.requestUserVerification(userIdToVerify);
    }

    public void receiveUserVerificationResult(String verifiedUserId, boolean result){
        for(AbstractUser abstractUser : userHive.getUsers()){
            Player player = castToPlayer(abstractUser);
            if(player.isUserIdAcquiredFromAuthServerIsEqual(verifiedUserId)){
                ((UserInformationReceiver)player.retrievePacketHandlerFromPipeline().getReceiver(GameServerReceivers.USER_INFORMATION)).sendVerificationResult(result);
            }
        }
    }

    private Player castToPlayer(AbstractUser abstractUser){
        return (Player) abstractUser;
    }

    public void setAuthenticated(boolean isAuthenticated){
        this.isAuthenticated = isAuthenticated;
    }

    public boolean isAuthenticated(){
        return isAuthenticated;
    }

    public void disconnectUser(String userId){
        userHive.removeUser(userId);
    }

    public Player getPlayerById(String userId){
        return (Player) userHive.getUserById(userId);
    }
}
