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


    public static void printer(String str, boolean newLine, Attribute color) {
        if (newLine) System.out.println(Ansi.colorize(str, color));
        else System.out.print(Ansi.colorize(str, color));
    }

    public static void errorPrinter(String str, boolean newLine) {
        if (newLine) System.out.println(fError.format(str));
        else System.out.print(fError.format(str));
    }
}