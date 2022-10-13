package no.ntnu.jorgfi.enigma.core.server;

import no.ntnu.jorgfi.enigma.app.server.ServerUDP;

/**
 * <b>Lancher of the ServerUDP application</b><p>
 * 
 * Will be run at the launch of the entire application.
 * Creates an instance of the ServerUDP-class and
 * executes a method-call to ServerUDP.run()
 * 
 * @author gruppe11
 * @version 30.09.22
 */
public class ServerLauncher {
    
    /**
     * Creates and runs the ServerUDP
     */
    public static void launch() {
        ServerUDP serverUDP = new ServerUDP();
        serverUDP.run();
    }
}
