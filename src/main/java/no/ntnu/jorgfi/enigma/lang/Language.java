package no.ntnu.jorgfi.enigma.lang;

public abstract class Language {
    private static String LANGUAGE;
    /*
     * ClientLauncher 
     * ----------------------------------------------------------------
    */
    /** Message to server */
    public static final String  L_CL_MESSAGE_REQUEST =  "";
    /** Successfully contacted ClientUDP part 1 */
    public static final String  L_CL_SUCCESS_1       =  "";
    /** Successfully contacted ClientUDP part 2 */
    public static final String  L_CL_SUCCESS_2       =  "";
    /** Error when running the ClientLauncher */
    public static final String  L_CL_ERROR           =  "";



    /*
     * ClientUDP 
     * ----------------------------------------------------------------
    */
    /** Server response + message @param message server response */
    public static final String L_C_RESPONSE = "";
    /** Error when running the ClientUDP*/
    public static final String L_C_ERROR      =  "";



    /*
     * ServerUDP 
     * ----------------------------------------------------------------
    */
    /** Active server message + address, part 1 @param ip address */
    public static final String L_S_ACTIVE_1   = "";
     /** Active server message + port, part 2 @param port number */
    public static final String L_S_ACTIVE_2   = "";
    /** Receiving a packet from ClientUDP part 1 */
    public static final String L_S_RECEIVED_1 = "";
    /** Receiving a packet from ClientUDP part 2 */
    public static final String L_S_RECEIVED_2 = "";
    /** Error when creating a new socket */
    public static final String L_S_SOCKET_ERROR = "";
    /** Error receiving or sending a datagram */
    public static final String L_S_COMMUNICATION_ERROR = "";
    /** Error receiving or sending a datagram */
    public static final String L_EXIT_MESSAGE = "";
}
