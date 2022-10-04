package no.ntnu.jorgfi.enigma.core.build;

import java.util.Arrays;
import java.util.stream.IntStream;

import config.Config;
import no.ntnu.jorgfi.enigma.lib.Address;
import no.ntnu.jorgfi.enigma.lib.Message;
import no.ntnu.jorgfi.enigma.lib.Port;
import no.ntnu.jorgfi.enigma.lib.Util;

/**
 * Applies the configurations from
 * Config.java
 * 
 * @author gruppe11
 * @version 02.10.22
 */
public class Build {

    /** Applicaction mode */
    public static boolean UDP_MODE = true;

    /** Full-auto mode */
    public static boolean FULL_AUTO = false;

    /* Server configurations */
    private static String activeIP = "";
    private static int activePort = -1; 


    /** Collection of valid port numbers for ip addresses */
    private static final String[][] VALID_COMBINATIONS = new String[][] {
        new String[] {"RPC", "HTTP", "HTTPS"},
        new String[] {"DEFAULT"}
    };
    
    /** Collection of corresponding variables */
    private static final int[][] PORT_NUMBER = new int[][] {
        new int[] {
            Port.LOCALHOST_RPC, 
            Port.LOCALHOST_HTTP,
            Port.LOCALHOST_HTTPS
        },
        new int[] {
            Port.TESTHOST
        }
    };


    /**
     * Applies all configurations to the application
     */
    public static void CONFIGURATE() {
        APPLY_LANGUAGE();
        APPLY_SERVER();
        APPLY_MODE();
        APPLY_COLOR();
        APPLY_FULL_AUTO();
    }



    private static void APPLY_COLOR() {
        Config.COLOR_CONFIG();
        Util.colorized = Config.colorized;
    }


    private static void APPLY_FULL_AUTO() {
        Config.FULL_AUTO_CONFIG();
        if ("localhost".equalsIgnoreCase(activeIP) && activePort == Port.LOCALHOST_RPC ) {
            FULL_AUTO = true;
        } else {
            System.out.print("\nConfig: Full-auto mode not compatible with ");
            System.out.print("other servers than localhost.");
            System.out.println("\nConfig: Full-auto disabled.");
        }
    }








    private static void APPLY_LANGUAGE() {
        Config.LANGUAGE_CONFIG();

        if ("norwegian".equalsIgnoreCase(Config.language)) {
            Message.lang = "norwegian";
        } else {
            Message.lang = "english";
        }
        Message.refresh();
    }

    
    /**
     * Apply application mode configuration based on MODE_CONFIG
    */
    private static void APPLY_MODE() {
        Config.MODE_CONFIG();
        UDP_MODE = Config.UDPmode;
    }
    

    /**
     * Apply server configuration based on SERVER_CONFIG
     */
    private static void APPLY_SERVER() {
        
        Config.SERVER_CONFIG();
        
        Config.serverIP = Config.serverIP.toLowerCase();
        Config.serverPort = Config.serverPort.toUpperCase();

        boolean valid_port;

        if ("localhost".equals(Config.serverIP)) {
            valid_port = Arrays.stream(VALID_COMBINATIONS[0])
                               .anyMatch(Config.serverPort::equals);
            
            Address.ACTIVE_HOST = Address.LOCALHOST;
            activeIP = "localhost";

            if (valid_port) {
                int port = PORT_NUMBER[0][FIND_INDEX(0)];
                Port.ACTIVE_PORT = port;
                activePort = port;
            }
            else {
                Port.ACTIVE_PORT = PORT_NUMBER[1][0];
                activePort = PORT_NUMBER[1][0];
                System.out.print("\nConfig: Invalid port \"" + Config.serverPort + "\"");
                System.out.print(" for server \"" + Config.serverIP + "\".");
                System.out.print("\nConfig: Deafult port for ");
                System.out.println( "\"" + Config.serverIP + "\" applied.");
            }
            
        } else if ("testhost".equals(Config.serverIP)) {
            valid_port = Arrays.stream(VALID_COMBINATIONS[1])
                               .anyMatch(Config.serverPort::equals);

            Address.ACTIVE_HOST = Address.TESTHOST;
            activeIP = "testhost";

            if (valid_port) {
                int port = PORT_NUMBER[1][FIND_INDEX(0)];
                Port.ACTIVE_PORT = port;
                activePort = port;
            }
            else {
                Port.ACTIVE_PORT = PORT_NUMBER[1][0];
                activePort = PORT_NUMBER[1][0];
                System.out.print("\nConfig: Invalid port \"" + Config.serverPort + "\"");
                System.out.print(" for server \"" + Config.serverIP + "\".");
                System.out.print("\nConfig: Deafult port for ");
                System.out.println( "\"" + Config.serverIP + "\" applied.");
            }
        } else {
            System.out.println("\nConfig: Invalid server option.");
            System.out.println("Config: Default configuration chosen.");
            activeIP = "localhost";
            activePort = Port.LOCALHOST_RPC;
        }
    }

    /**
     * Find index of given item
     * @return index of given item
     */
    private static int FIND_INDEX(int NUM) {
        try {
            return IntStream.range(0, VALID_COMBINATIONS[NUM].length)
                            .filter(i -> Config.serverPort
                            .equalsIgnoreCase(VALID_COMBINATIONS[NUM][i]))
                            .findFirst().getAsInt();
        } catch (Exception e) {
            return 0;
        }
    }
}
