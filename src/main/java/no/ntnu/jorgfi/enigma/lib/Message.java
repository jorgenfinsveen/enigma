package no.ntnu.jorgfi.enigma.lib;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.jorgfi.enigma.lang.English;
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
    
    /** REGEX */
    private static final String REGEX = "^[a-zA-Z0-9]";

    
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
    /** The word "Client: " */
    public static String C            = getMessage(14); // ID = 14



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
    /** English version of "ok" when a task is completed with correct answers */
    public static final String S_OUT_OK = "ok"; 
    /** English version of "error" when a task is answered incorrectly */
    public static final String S_OUT_ERROR = "error"; 
    /** English version of "Invalid request" when a command is not "task" when "task" is expected */
    public static final String S_OUT_INVALID = "invalid request";  


    /*
     * TERMINAL VALID INPUTS FROM USER TO CLIENT AND SERVER
     * ----------------------------------------------------------------
    */
    /** English version of valid input "task" */
    public static final String C_IN_TASK = "task"; 
    /** English version of valid input "disconnect" */
    public static final String C_IN_DISCONNECT = "disconnect"; 
    /** English version of valid input "question" */
    public static final String C_IN_QUESTION = "question"; 
    /** English version of valid input "statement" */
    public static final String C_IN_STATEMENT = "statement"; 


    /** HashMap containing all valid in- and outputs in english/norwegian */
    private static final HashMap<String, String> EN_NO = new HashMap<String, String>(){
        {
            /* Server outputs */
            put(S_OUT_OK, Norwegian.S_OUT_OK);
            put(S_OUT_ERROR, Norwegian.S_OUT_ERROR);
            put(S_OUT_INVALID, Norwegian.S_OUT_INVALID);

            /* Valid commands */
            put(C_IN_TASK, Norwegian.C_IN_TASK);
            put(C_IN_DISCONNECT, Norwegian.C_IN_DISCONNECT);
            put(C_IN_DISCONNECT, Norwegian.C_IN_DISCONNECT);
            put(C_IN_QUESTION, Norwegian.C_IN_QUESTION);
            put(C_IN_STATEMENT, Norwegian.C_IN_STATEMENT);
        }
    };

    /**
     * Reverses a language-HashMap-container so that keys are values
     * and vice versa.
     */
    private static <A, B> HashMap<B, A> invertMap(HashMap<A, B> map) {
        HashMap<B, A> reverseMap = new HashMap<>();
        for (Map.Entry<A, B> entry : map.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        return reverseMap;
    }

    /**
     * Translates inputs from norwegian to english.
     * @param sentence to translate.
     * @return translated sentence if parameter is valid.
     */
    public static String translateInput(String sentence) {
        String translated = sentence;
        if (!"english".equalsIgnoreCase(Message.lang)) {
            /* The program has trouble with nordic characters */
            if (!sentence.matches(REGEX)) {
                if (sentence.charAt(0) == 'p') sentence = Norwegian.C_IN_STATEMENT;
                else if (sentence.charAt(0) == 's') sentence = Norwegian.C_IN_QUESTION;
            } 
            if (Message.EN_NO.containsValue(sentence)) {
                HashMap<String, String> reversed = invertMap(EN_NO);
                translated = reversed.get(sentence);
            }
        }
        return translated;
    }

    /**
     * Translates inputs from norwegian to english.
     * @param sentence to translate.
     * @return translated sentence if parameter is valid.
     */
    public static String translateOutput(String sentence) {
        String translated = sentence;
        if (!"english".equalsIgnoreCase(Message.lang)) {
            if (!sentence.matches(REGEX) && sentence.charAt(0) == 'u') {
                sentence = Norwegian.S_OUT_INVALID;
            }
            if (Message.invertMap(EN_NO).containsValue(sentence)) {
                translated = EN_NO.get(sentence);
            }
        }
        return translated;
    }


    /*
     *  1. Lage variabler som bruker getMessage for:
     *      1. Input-verdier som er valid. (Engelsk)
     *      2. Output-verdier som er valid. (Engelsk)
     *    NB: Disse må nummereres fra 14 og utover.
     * 2. Lage tilsvarende variabler i English.java og Norwegian.java.
     * !(3). Sett inn getMessage til alle kommandoer for input og output
     *      inni refresh() metoden nedenunder. (Kanskje unødvendig dersom stringene lagres som engelsk her, og evt norske setninger blir hentet ut av Norwegian på et eller annet vis. Kan evt bruke en egen ny metode for å hente ut norsk motstykke av engelsk input/output.)
     * 4. I ServerUDP, lag en translate()-metode som tar imot en string.
     *      Det den skal gjøre er:
     *          1. Sjekke lang-variabelen i denne klassen.
     *          2. Dersom språket er engelsk, ikke gjør noe mer. Ellers:
     *              3. Sjekke om parameteret matcher et av input/output variablene som er lagret som valid
     *              4. Dersom valid skal parameteret byttes ut mot tilsvarende string på engelsk.
     *              5. Dersom ugyldig skal parameteret bare returneres.
     */




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
        C                       = getMessage(14);      
    }
}