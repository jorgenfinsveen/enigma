package no.ntnu.jorgfi.enigma.lib;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;

import config.Config;
import no.ntnu.jorgfi.enigma.tools.FileHandler;

/**
 * <b>Pool of objects used in the application</b><p>
 * 
 * Holds objects like Scanner used in parts
 * of the application as public fields which can be
 * reached by the different programs. 
 * 
 * @author gruppe11
 * @version 30.09.22
 */
public class Util {

    /** Colorized mode. Colorful terminal when colorized = true */
    public static boolean colorized = true;

    /** Scanner-object for reading input from STDIN */
    public static final Scanner TERMINAL =  new Scanner(System.in);

    /** Socket which the client uses to connect to the server */
    public static DatagramSocket CLIENT_SOCKET;

    /** Socket which the server uses to connect to the client */
    public static DatagramSocket SERVER_SOCKET;

    /** Client text color for STDOUT. Making it easier to divide server and client */
    public static final Attribute CLIENT_COLOR = Attribute.TEXT_COLOR(21);

    /** Server text color for STDOUT. Making it easier to divide server and client */
    public static final Attribute SERVER_COLOR = Attribute.TEXT_COLOR(11);

    /** Disconnecting text color for STDOUT */
    public static final Attribute EXIT_COLOR = Attribute.TEXT_COLOR(1);

    /** Invalid text color for STDOUT */
    public static final Attribute INVALID_COLOR = Attribute.TEXT_COLOR(130);

    /** Error text color for STDOUT */
    private static AnsiFormat fError = new AnsiFormat(Attribute.YELLOW_TEXT(), Attribute.RED_BACK());

    /** File path */
    public static Path PATH = getPath();



    /**
     * Initializes the sockets after the address and ports
     * are defined in the configuration.
     * @throws SocketException
     */
    public static void initSocket() throws SocketException {
        try {
            CLIENT_SOCKET = new DatagramSocket();
            SERVER_SOCKET = new DatagramSocket(Port.ACTIVE_PORT);
        } catch (Exception e) {
            /* Prints error message to STDOUT */
            Util.errorPrinter(Message.S_SOCKET_ERROR, true);
            throw new SocketException();
        }
    }


    /**
     * Initializes the file-path after getting the language
     * which are defined in the configuration.
     */
    public static void initPath() {
        if ("norwegian".equalsIgnoreCase(Config.language)) {
            PATH = Paths.get("src/main/java/no/ntnu/jorgfi/enigma/database/sentences_no.csv");
        } else {
            PATH = Paths.get("src/main/java/no/ntnu/jorgfi/enigma/database/sentences_en.csv");
        }
        FileHandler.setPath(PATH);
    }

    /**
     * Get the file-path based on the language
     * which are defined in the configuration.
     */
    public static Path getPath() {
        FileHandler.setPath(PATH);
        return PATH;
    }


    /**
     * Output to STDOUT with formatting
     * @param string to print
     * @param newLine true when lineshifting
     * @param color of the text
    */
    public static void printer(String string, boolean newLine, Attribute color) {
        if (colorized) {
            if (newLine) System.out.println(Ansi.colorize(string, color));
            else System.out.print(Ansi.colorize(string, color));
        } else {
            if (newLine) System.out.println(string);
            else System.out.print(string);
        }
    }

    /**
     * ERROR to STDOUT with formatting
     * @param string to print
     * @param newLine true when lineshifting
    */
    public static void errorPrinter(String string, boolean newLine) {
        if (colorized) {
            if (newLine) System.out.println(fError.format(string));
            else System.out.print(fError.format(string));
        } else {
            if (newLine) System.out.println(string);
            else System.out.print(string);
        }
    }
}