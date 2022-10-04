package no.ntnu.jorgfi.enigma.lang;

public class English extends Language {

    private static final String LANGUAGE = "english";

    /*
     * ClientLauncher 
     * ----------------------------------------------------------------
    */
    /** Message to server */
    public static final String  L_CL_MESSAGE_REQUEST =  "\nMessage to server: ";
    /** Successfully contacted ClientUDP part 1 */
    public static final String  L_CL_SUCCESS_1         =  "Establishing client-server UDP connection... ";
    /** Successfully contacted ClientUDP part 2 */
    public static final String  L_CL_SUCCESS_2         =  "success";
    /** Error when running the ClientLauncher */
    public static final String  L_CL_ERROR           =  "\nProgram failed to read user input. Closing down...";



    /*
     * ClientUDP 
     * ----------------------------------------------------------------
    */
    /** Server response + message @param message server response */
    public static final String L_C_RESPONSE   = "Server responded: ";
    /** Error when running the ClientUDP*/
    public static final String L_C_ERROR      =  "\nFailed to run the UDP client: \n";



    /*
     * ServerUDP 
     * ----------------------------------------------------------------
    */
    /** Active server message + address, part 1 @param ip address */
    public static final String L_S_ACTIVE_1   = "\n\nServer IP-address: ";
     /** Active server message + port, part 2 @param port number */
    public static final String L_S_ACTIVE_2   = "\nActive at port: ";
    /** Receiving a packet from ClientUDP part 1 */
    public static final String L_S_RECEIVED_1 = "\nReceived UDP packet from: ";
    /** Receiving a packet from ClientUDP part 2 */
    public static final String L_S_RECEIVED_2 = " at port: ";
    /** Error when creating a new socket */
    public static final String L_S_SOCKET_ERROR = "\nError creating socket. Server closing down...";
    /** Error receiving or sending a datagram */
    public static final String L_S_COMMUNICATION_ERROR = "\nError receiving or sending a datagram: \n";
    /** Error receiving or sending a datagram */
    public static final String L_EXIT_MESSAGE = "\nDisconnecting...";

    /** Collection of all messages in this particular language */
    public static final String[] MESSAGES = new String[] {
        /* Language pack */
        LANGUAGE,
        /* ClientLauncher */
        L_CL_MESSAGE_REQUEST,
        L_CL_SUCCESS_1,
        L_CL_SUCCESS_2,
        L_CL_ERROR,
        /* ClientUDP */
        L_C_RESPONSE,
        L_C_ERROR,
        /* ServerUDP */
        L_S_ACTIVE_1,
        L_S_ACTIVE_2,
        L_S_RECEIVED_1,
        L_S_RECEIVED_2,
        L_S_SOCKET_ERROR,
        L_S_COMMUNICATION_ERROR,
        L_EXIT_MESSAGE
    };
}
