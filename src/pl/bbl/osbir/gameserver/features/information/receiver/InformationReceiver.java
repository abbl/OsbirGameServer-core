package pl.bbl.osbir.gameserver.features.information.receiver;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.gameserver.authserver.connection.AuthenticationServerConnection;
import pl.bbl.osbir.gameserver.features.information.InformationExchanger;

public class InformationReceiver extends PacketReceiver {
    private AuthenticationServerConnection connection;

    public InformationReceiver(String receiverType, AuthenticationServerConnection connection) {
        super(receiverType);
        this.connection = connection;
    }

    @Override
    public boolean receive(Packet packet) {
        switch(packet.packetPurpose){
            case "USER_AUTHENTICATION_RESULT":
                return true;
        }
        return false;
    }
}
