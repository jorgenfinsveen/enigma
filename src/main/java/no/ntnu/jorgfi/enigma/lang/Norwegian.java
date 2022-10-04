package no.ntnu.jorgfi.enigma.lang;

public class Norwegian extends Language {

    private static String LANGUAGE = "Norwegian";

    /*
     * ClientLauncher 
     * ----------------------------------------------------------------
    */
    /** Message to server */
    public static final String  L_CL_MESSAGE_REQUEST =  "\nMelding til server: ";
    /** Successfully contacted ClientUDP part 1 */
    public static final String  L_CL_SUCCESS_1         =  "Oppretter klient-server UDP tilkonling... ";
    /** Successfully contacted ClientUDP part 2 */
    public static final String  L_CL_SUCCESS_2         =  "suksess";
    /** Error when running the ClientLauncher */
    public static final String  L_CL_ERROR           =  "\nProgrammet kunne ikke lese input. Avslutter...";



    /*
     * ClientUDP 
     * ----------------------------------------------------------------
    */
    /** Server response + message @param message server response */
    public static final String L_C_RESPONSE   = "Server svarte: ";
    /** Error when running the ClientUDP*/
    public static final String L_C_ERROR      =  "\nUDP klienten svarer ikke: \n";



    /*
     * ServerUDP 
     * ----------------------------------------------------------------
    */
    /** Active server message + address, part 1 @param ip address */
    public static final String L_S_ACTIVE_1   = "\n\nServerens IP-adresse: ";
     /** Active server message + port, part 2 @param port number */
    public static final String L_S_ACTIVE_2   = "\nAktiv port: ";
    /** Receiving a packet from ClientUDP part 1 */
    public static final String L_S_RECEIVED_1 = "\nMotatt UDP datapakke fra: ";
    /** Receiving a packet from ClientUDP part 2 */
    public static final String L_S_RECEIVED_2 = " ved port: ";
    /** Error when creating a new socket */
    public static final String L_S_SOCKET_ERROR = "\nError ved opprettelse av ny socket. Serveren avslutter...";
    /** Error receiving or sending a datagram */
    public static final String L_S_COMMUNICATION_ERROR = "\nError ved mottak eller sending av et datagram: \n";
    /** Error receiving or sending a datagram */
    public static final String L_EXIT_MESSAGE = "\nKobler av...";

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
