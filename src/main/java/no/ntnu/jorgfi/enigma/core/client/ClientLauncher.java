package no.ntnu.jorgfi.enigma.core.client;

import com.diogonunes.jcolor.Attribute;

import no.ntnu.jorgfi.enigma.app.client.ClientUDP;
import no.ntnu.jorgfi.enigma.lib.Message;
import no.ntnu.jorgfi.enigma.lib.Util;

/**
 * <b>Lancher of the ClientUDP application</b><p>
 * 
 * Will be run at the launch of the entire application.
 * Creates an instance of the ClientUDP-class and
 * executes a method-call to ClientUDP.run() upon
 * correct initialization of the ClientUDP.
 * 
 * @author gruppe11
 * @version 30.09.22
 */
public class ClientLauncher {

    /** Used to avoid printing Message.CL_SUCCESS for each call. */
    private static boolean connected = false;
    

    /**
     * Creates and runs the ClientUDP with the parameter
     * specified by the user-input to the terminal.
     */
    public static void launch() {

        /* 
         * Will try to read input from STDIN and launch the ClientUDP 
         * with the input as parameter.
        */
            
        /* Only prints at first call to main() */
        if (!connected) { 
            System.out.print(Message.CL_SUCCESS_1 + "\"");
            Util.printer(Message.CL_SUCCESS_2, false, Attribute.GREEN_TEXT());
            System.out.println("\"\n");

            /* Sets the field conncted to true */
            connected = true;
        }


        /* Prepares the ClientUDP */
        ClientUDP clientUDP = new ClientUDP();

        /* Launches the client */
        clientUDP.run();
    }
}
