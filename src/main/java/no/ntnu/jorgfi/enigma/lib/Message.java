package no.ntnu.jorgfi.enigma.lib;

import no.ntnu.jorgfi.enigma.lang.English;
import no.ntnu.jorgfi.enigma.lang.Language;
import no.ntnu.jorgfi.enigma.lang.Norwegian;

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
     * LANGUAGE
     * ----------------------------------------------------------------
    */
    public static String lang = "english"; // ID = 0
    
    /**
     * Gets a given string from correct language pack.
     * @param id of message
     * @return actual message from language pack
     */
    private static String getMessage(int id) {
        if ("norwegian".equalsIgnoreCase(lang)) {
            return Norwegian.MESSAGES[id];
        } else {
            return English.MESSAGES[id];
        }
    }
    

    
    /*
     * ClientLauncher 
     * ----------------------------------------------------------------
    */
    /** Message to server */
    public static String  CL_MESSAGE_REQUEST =  getMessage(1); // ID = 1
    /** Successfully contacted ClientUDP part 1 */
    public static String  CL_SUCCESS_1       =  getMessage(2); // ID = 2
    /** Successfully contacted ClientUDP part 2 */
    public static String  CL_SUCCESS_2       =  getMessage(3); // ID = 3
    /** Error when running the ClientLauncher */
    public static String  CL_ERROR           =  getMessage(4); // ID = 4



    /*
     * ClientUDP 
     * ----------------------------------------------------------------
    */
    /** Server response + message @param message server response */
    public static String C_RESPONSE   = getMessage(5); // ID = 5
    /** Error when running the ClientUDP*/
    public static String C_ERROR      = getMessage(6); // ID = 6



    /*
     * ServerUDP 
     * ----------------------------------------------------------------
    */
    /** Active server message + address, part 1 @param ip address */
    public static String S_ACTIVE_1               = getMessage(7); // ID = 7
     /** Active server message + port, part 2 @param port number */
    public static String S_ACTIVE_2               = getMessage(8); // ID = 8
    /** Receiving a packet from ClientUDP part 1 */
    public static String S_RECEIVED_1             = getMessage(9); // ID = 9
    /** Receiving a packet from ClientUDP part 2 */
    public static String S_RECEIVED_2             = getMessage(10); // ID = 10
    /** Error when creating a new socket */
    public static String S_SOCKET_ERROR           = getMessage(11); // ID = 11
    /** Error receiving or sending a datagram */
    public static String S_COMMUNICATION_ERROR    = getMessage(12);  // ID = 12
    /** Error receiving or sending a datagram */
    public static String EXIT_MESSAGE             = getMessage(13); // ID = 13









    public static void refresh() {
        CL_MESSAGE_REQUEST      =  getMessage(1);
        CL_SUCCESS_1            =  getMessage(2);
        CL_SUCCESS_2            =  getMessage(3);
        CL_ERROR                =  getMessage(4);

        C_RESPONSE              = getMessage(5);
        C_ERROR                 = getMessage(6);

        S_ACTIVE_1              = getMessage(7);
        S_ACTIVE_2              = getMessage(8);
        S_RECEIVED_1            = getMessage(9);
        S_RECEIVED_2            = getMessage(10);
        S_SOCKET_ERROR          = getMessage(11);
        S_COMMUNICATION_ERROR   = getMessage(12);
        EXIT_MESSAGE            = getMessage(13);
    }
}