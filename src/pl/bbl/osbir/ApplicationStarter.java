package pl.bbl.osbir;

import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;

public class ApplicationStarter {
    public static void main(String args[]){
        SegmentsCommunicationDirector segmentsCommunicationDirector = new SegmentsCommunicationDirector();
        segmentsCommunicationDirector.connectToAuthenticationServer();
        segmentsCommunicationDirector.requestGameServerAuthentication();
        segmentsCommunicationDirector.startGameServer();
        segmentsCommunicationDirector.updateGameServerInformationInAuthServer();
    }
}
