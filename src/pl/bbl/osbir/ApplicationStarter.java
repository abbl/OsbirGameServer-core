package pl.bbl.osbir;

import pl.bbl.network.tools.LogType;
import pl.bbl.network.tools.NetworkLogger;
import pl.bbl.osbir.gameserver.SegmentsCommunicationDirector;

public class ApplicationStarter {
    public static void main(String args[]){
        NetworkLogger.changeWorkingMode(LogType.DEBUG);
        SegmentsCommunicationDirector segmentsCommunicationDirector = new SegmentsCommunicationDirector();
        segmentsCommunicationDirector.establishConnectionToAuthenticationServer();
        segmentsCommunicationDirector.requestGameServerAuthentication();
    }
}
