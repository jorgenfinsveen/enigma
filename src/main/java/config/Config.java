package config;

/**
 * Configuration-file for UDP
 * the application.
 * 
 * @author gruppe11
 * @version 02.10.22
 */
public class Config {

    /** IP-Address of server */
    public static String serverIP = "";
    /** Port number of server */
    public static String serverPort = "";
    /** UDP-mode at true. ServerUI at false */
    public static boolean UDPmode = true;

   


    /**
     * <h2>ServerUDP test configuration.</h2>
     * <p>
     * Change variables to alter 
     * current configuration.
     * <p>
     * <h3>Options: </h3>
     * <b>IP: "localhost"</b>
     * <p><ul>
     *  <li> Port: "rpc"
     *  <li> Port: "http"
     *  <li> Port: "https"
     * </ul></p>
     * <b>IP: "testhost"</b>
     * <p><ul>
     *  <li> Port: "default"
     * </ul>
     * </p>
     * Invalid configuration will result in default
     * localhost configuration.
     * @param host localhost or external host
     * @param port number of server
     */
    public static void SERVER_CONFIG() {
        
        /*
         * IP-address of the UDP task server
        */
        serverIP = "localhost";

        /*
         * Port-number of the UDP task server
        */
        serverPort = "rpc";
    }



    /**
     * <h2>Change application mode</h2>
     * <p>
     * There are two modes
     * @true UDP-Mode - Uses UDP client-server communication (default)
     * @false ServerUI-Mode - Uses interacts directly to a server UI
     */
    public static void MODE_CONFIG() {

        /*
         * Change application mode.
        */
        UDPmode = true;
    }
}