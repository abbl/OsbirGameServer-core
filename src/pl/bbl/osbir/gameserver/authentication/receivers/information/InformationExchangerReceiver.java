package pl.bbl.osbir.gameserver.authentication.receivers.information;

import pl.bbl.network.client.ClientConnection;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handler.PacketReceiver;
import pl.bbl.osbir.gameserver.authentication.connection.AuthenticationConnection;

public class InformationExchangerReceiver extends PacketReceiver{
    private static AuthenticationConnection authenticationConnection;

    public InformationExchangerReceiver(AuthenticationConnection authenticationConnection) {
        super("INFORMATION_EXCHANGER");
        InformationExchangerReceiver.authenticationConnection = authenticationConnection;
    }

    @Override
    public void receivePacket(Packet packet, ClientConnection clientConnection) {
        switch (packet.getPacketType()){
            case "USER_VERIFICATION_RESULT":
                passVerificationResult(packet);
        }
    }

    private void passVerificationResult(Packet packet) {
        authenticationConnection.passVerificationResult((String) packet.getData("userKey"), (String) packet.getData("username"), (boolean) packet.getData("result"));
    }
}
