package no.ntnu.jorgfi.enigma.lib;

import java.util.Scanner;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;

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