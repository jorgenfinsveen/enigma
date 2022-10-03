package no.ntnu.jorgfi.enigma.lib;

/**
 * <b>Pool of STDOUT messages</b><p>
 * 
 * Holds all the STDOUT messages of the different parts
 * of the application as public fields which can be
 * reached by the different programs. This makes it
 * easier to modify outputs throughout the application.
 * 
 * @author gruppe11
 * @version 30.09.22
 */
public class Message {
    
    /*
     * ClientLauncher 
     * ----------------------------------------------------------------
    */
    /** Message to server */
    public static final String  CL_MESSAGE_REQUEST =  "\nMessage to server: ";
    /** Successfully contacted ClientUDP part 1 */
    public static final String  CL_SUCCESS_1         =  "Establishing client-server UDP connection... ";
    /** Successfully contacted ClientUDP part 2 */
    public static final String  CL_SUCCESS_2         =  "success";
    /** Error when running the ClientLauncher */
    public static final String  CL_ERROR           =  "\nProgram failed to read user input. Closing down...";



    /*
     * ClientUDP 
     * ----------------------------------------------------------------
    */
    /** Server response + message @param message server response */
    public static final String C_RESPONSE   = "Server responded: ";
    /** Error when running the ClientUDP*/
    public static final String C_ERROR      =  "\nFailed to run the UDP client: \n";



    /*
     * ServerUDP 
     * ----------------------------------------------------------------
    */
    /** Active server message + address, part 1 @param ip address */
    public static final String S_ACTIVE_1   = "\n\nServer IP-address: ";
     /** Active server message + port, part 2 @param port number */
     public static final String S_ACTIVE_2   = "\nActive at port: ";
    /** Receiving a packet from ClientUDP part 1 */
    public static final String S_RECEIVED_1 = "\nReceived UDP packet from: ";
    /** Receiving a packet from ClientUDP part 2 */
    public static final String S_RECEIVED_2 = " at port: ";
    /** Error when creating a new socket */
    public static final String S_SOCKET_ERROR = "\nError creating socket. Server closing down...";
    /** Error receiving or sending a datagram */
    public static final String S_COMMUNICATION_ERROR = "\nError receiving or sending a datagram: \n";
    /** Error receiving or sending a datagram */
    public static final String EXIT_MESSAGE = "\nDisconnecting...";
}