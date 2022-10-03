package no.ntnu.jorgfi.enigma.lib;

/**
 * <b>Pool of IP-Addresses</b><p>
 * 
 * Holds all the IP-addresses available as public
 * fields which can be reached by the different 
 * programs. This makes it easier to modify the
 * addresses throughout the application.
 * 
 * @author gruppe11
 * @version 30.09.22
 */
public class Address {
    /** Localhost, meaning this machine  */
    public static final String LOCALHOST    = "127.0.0.1"; 
    /** Testhost, a server the teachers at NTNU has set up for testing the UDP client */       
    public static final String TESTHOST     = "129.241.152.12";     

    /** Current host in use for testing of the UDP client */
    public static String ACTIVE_HOST = LOCALHOST;

    /*
     * DEFAULT: LOCALHOST 127.0.0.1
    */
}