package config;

/**
 * <h2>Configuration-file for UDP the application</h2>
 * <p>
 * Do only change the values defined inside the <code>
 * public static void XXX_CONFIG()</code> methods. 
 * Valid parameters for each configuration method are 
 * specified in the Javadoc above each configuration method.
 * <p>
 * <b>NB:</b> <i>Remember to save the file before running the
 * application for the configurations to be applied.</i>
 * 
 * @author gruppe11
 * @version 02.10.22
 */
public class Config {

    /*
     * DO NOT EDIT THE FIELDS IN THIS BOX
     * ------------------------------------------------------------------------------
    */

    /** IP-Address of server */
    public static String serverIP = "localhost"; // default: "localhost"
    /** Port number of server */
    public static String serverPort = "rpc"; // default: "rpc"
    /** UDP-mode at true. ServerUI at false */
    public static boolean UDPmode = true; // default: true
    /** Language of application */
    public static String language = "english"; // default: "english"
    /** Colorization. Colorized at true. */
    public static boolean colorized = true; // default: true
    /** Full-auto mode. Full-auto at true. */
    public static boolean fullAuto = false; // default: false

    /*
     * ------------------------------------------------------------------------------
    */
   



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
     *  <li> Port: "http" - (doesnt support UDP)
     *  <li> Port: "https" - (doesnt support UDP)
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



    /**
     * <h2>Application language</h2>
     * <p>
     * Languages:
     * @english <code>language = "english";</code>
     * @norwegian <code>language = "norwegian";</code>
     */
    public static void LANGUAGE_CONFIG() {

        /* 
         * Change language.
        */
        language = "english";
    }



    /**
     * <h2>Colorized terminal</h2>
     * <p>
     * Colorizes the terminal, making it
     * easier to distinguish from server
     * and client. (Works only for UDP-Mode)
     * @true Colorized (default)
     * @false Not colorized
     */
    public static void COLOR_CONFIG() {

        /*
         * Switch from colorized terminal to non-colorized terminal
        */
        colorized = true;
    }



    /**
     * <h2>Full-auto mode</h2>
     * <p>
     * Enables full-auto, which implies that the user
     * does not interact with the application. The server
     * will send all test-sentences it has available to
     * the client, and the client will analyze all of them
     * with an analyzer-tool, and send the results back to the
     * server. The server will return statistics regarding the
     * performance of the analyzation made by the client.
     * <p>
     * <b>Note:</b> Full-auto mode is only supported by the 
     * default server (localhost), and the server will send
     * all test-sentences in the specified application-language.
     * If full-auto mode is enabled with not-default server
     * configuration, the application will inform the user about
     * this, and turn off full-auto.
     * @true Full-auto
     * @false Not full-auto (default)
     */
    public static void FULL_AUTO_CONFIG() {

        /*
         * Switch between full-auto mode and default mode
        */
        fullAuto = false;
    }
}