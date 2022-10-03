package no.ntnu.jorgfi.enigma.lib;

/**
 * <b>Pool of server-ports</b><p>
 * 
 * Holds all the server-ports corresponding to a
 * server-address available as public fields 
 * which can be reached by the different 
 * programs. This makes it easier to modify ports
 * throughout the application.
 * 
 * @author gruppe11
 * @version 30.09.22
 */
public class Port {
    
    /*
     * Localhost 
     * ----------------------------------------------------------------
    */
    /** Sun RPC port at localhost */
    public static final int LOCALHOST_RPC       = 9876;    
    /** HTTP port at localhost */
    public static final int LOCALHOST_HTTP      = 80;   // Not UDP-compatible
    /** HTTPS port at localhost */
    public static final int LOCALHOST_HTTPS     = 443;  // Not UDP-compatible



    /*
     * ClientLauncher 
     * ----------------------------------------------------------------
    */
    /** UDP port at testhost */
    public static final int TESTHOST    = 1234;     


    /** Port in use for testing of the UDP client */
    public static int ACTIVE_PORT = LOCALHOST_RPC;

    /*
     * DEFAULT: LOCALHOST_RPC 9876
    */
}
