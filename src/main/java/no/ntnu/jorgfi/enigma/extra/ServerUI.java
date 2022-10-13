package no.ntnu.jorgfi.enigma.extra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import no.ntnu.jorgfi.enigma.lib.Message;
import no.ntnu.jorgfi.enigma.lib.Util;
import no.ntnu.jorgfi.enigma.model.Analyzer;

/**
 * <b>UDP-client application</b><p>
 * 
 * Represents a Server UI, where a client can request a task
 * to be done by the different server services.
 * This server is meant to analyze one or several sentences
 * and inform the client whether the sentence is a question
 * or a statement, and the amount of words in the sentence.
 * 
 * @version 29.09.22
 * @author gruppe11
 */
public class ServerUI {
    
    // Scanner object reading from STDIN
    private static final Scanner scanner = new Scanner(System.in); 
    // Regex format for making even spaces between results
    private static final String FORMAT = "%-20s%s%n"; 
    // Separator for making the UI clearer
    private static final String LINES = "--------------------------------------"; 


    /**
     * Initializes the program by printing a welcoming
     * message and entering the loop inside the start()
     * method if the client sends the <code>task</code>
     * request.
     */
    public static void launch() {
        Boolean requested = false;
        System.out.println();
        Util.printer(
            "\nWELCOME TO SERVER UI",
            true, 
            Util.SERVER_COLOR
        );
        System.out.println(LINES);
        askForInput();

        String input = scanner.nextLine();
        System.out.println();

        checkDisconnect(input);

        while (!requested) {
            checkDisconnect(input);
            if ("task".equals(input)) {
                requested = true;
                showInstructions();
                askForTask();
                start(true);
            } else {
                Util.printer(
                    "Invalid initializing command.", 
                    true, 
                    Util.INVALID_COLOR
                );
                System.out.println("\n" + LINES);
                askForInput();
                input = scanner.nextLine();
                System.out.println();
            }
        }
    }

    
    /**
     * Starts listening for a specified task, and passes it in
     * appropriate format to the reach() method.
     * @param running <code>true</code> until the client disconnects
     */
    public static void start(Boolean running) {  
        if (running) {
            String input = scanner.nextLine();
            reach(input.toLowerCase());
            System.out.println(LINES);
        }
    }


    /**
     * Determines which task to execute based on the clients
     * request.
     * @param task name of the task to execute (String)
     */
    public static void reach(String task) {
        switch (task) {

            // Client requests a single analyzation
            case "analyze one sentence":
                analyzeOneSentence();
                break;
            case "1":
                analyzeOneSentence();
                break;
            
            // Client requests multiple analyzations
            case "analyze several sentences":
                analyzeSeveralSentences();
                break;
            case "2":
                analyzeSeveralSentences();
                break;

            // Client wants to disconnect
            case "disconnect":
                Util.printer(
                    "ServerUI: ",
                    false,
                    Util.CLIENT_COLOR
                );
                Util.printer(
                    "\nDisconnecting...", 
                    true, 
                    Util.EXIT_COLOR
                );
                scanner.close();
                System.exit(0);
                break;

            // Unknown request
            default:
                System.out.println("\n" + LINES);
                Util.printer(
                    "The task, \"" + task + "\", is not valid.\n", 
                    true, 
                    Util.INVALID_COLOR
                );
                showInstructions();
                askForTask();
                start(true);
        }
    }


    /**
     * Listens for the sentence given by the client.
     * Will run the sentence through the Analyzer-program,
     * and send it to the printResult() formatter.
     */
    private static void analyzeOneSentence() {
        Util.printer(
                    "\nSentence: ", 
                    false, 
                    Util.CLIENT_COLOR
        );
        String input = scanner.nextLine();
        checkDisconnect(input);
        System.out.println("\n" + LINES);
        printResult(Analyzer.analyze(input));
        System.out.println(LINES + "\n");
        askForTask();
        start(true);
    }

    
    /**
     * Listens for first the amount of sentences which
     * are to be analyzed followed by the subject sentences.
     * All valid sentences will be runned through the 
     * Analyzer-program, and sent to the printResults()
     * formatter.
     */
    private static void analyzeSeveralSentences() {
        boolean valid = false;
        int amount = 0;
        while (!valid) {
            Util.printer(
                    "\nAmount of sentences: ", 
                    false, 
                    Util.CLIENT_COLOR
            );
            String amountInput = scanner.nextLine();
            try {
                amount = Integer.parseInt(amountInput);
                valid = true;
            } catch (Exception e) {
                valid = false;
                checkDisconnect(amountInput);
                Util.printer(
                    "Invalid number. Try again.", 
                    true, 
                    Util.INVALID_COLOR
                );
            }
        }
        
        int index = 0;
        String[] sentences = new String[amount];
        String input;
        Util.printer(
                    "\nSentences (Separate using enter): ", 
                    true, 
                    Util.CLIENT_COLOR
        );
        
        for (int i = 0; i <= amount-1; i++) {
            input = scanner.nextLine();
            checkDisconnect(input);
            // Sometimes, the scanner unintentionally register blank lines
            if (!input.isEmpty()) {
                sentences[index] = input;
                index++;
            }
            scanner.reset();
        }
        System.out.println("\n" + LINES);
        printResults(Analyzer.analyzeList(sentences));
        System.out.println(LINES + "\n");
        askForTask();
        start(true);
    }


    /**
     * Prints the result of the analyzation to the console.
     * @param result List containing the results of the analyzation
     */
    private static void printResult(List<String> result) {
        String type = ( ("true".equals(result.get(0))) ? "question" : "statement" );
        System.out.format(FORMAT, "Type: " + type, "Amount of words: " + result.get(1));
    }


    /**
     * Prints the result of all analyzations to the console.
     * @param results List containing the results of all analyzations
     */
    private static void printResults(List<ArrayList<String>> results) {
        for (ArrayList<String> result : results) printResult(result);
    }


    /**
     * Prints the name of the available services to the console.
     */
    private static void showInstructions() {
        Util.printer(
                    "\nThe available services are:", 
                    true, 
                    Util.SERVER_COLOR
        );
        Util.printer(
                    "  1. (analyze one sentence)", 
                    true, 
                    Util.SERVER_COLOR
        );
        Util.printer(
                    "  2. (analyze several sentences)", 
                    true, 
                    Util.SERVER_COLOR
        );
        Util.printer(
                    "  - disconnect", 
                    true, 
                    Util.SERVER_COLOR
        );
        System.out.println(LINES + "\n");
    }

    /**
     * Asks the user to type in the initializing command.
     */
    private static void askForInput() {
        Util.printer(
            "Enter the initializing command ('task') to continue: ", 
            false, 
            Util.CLIENT_COLOR
        );
    }

    /**
     * Asks the user to specify the task to execute.
     */
    private static void askForTask() {
        Util.printer(
            "Write the name (or the number) of the task to be executed and press enter: ", 
            false, 
            Util.CLIENT_COLOR
        );
    }

    /**
     * Check if disconnect is requested.
     */
    private static void checkDisconnect(String input) {
        if ("disconnect".equals(input)) {
            Util.printer(
                "ServerUI: ",
                false,
                Util.CLIENT_COLOR
            );

            Util.printer(
                Message.EXIT_MESSAGE,
                true,
                Util.EXIT_COLOR
            );
            scanner.close();
            System.exit(0);
        }
    }
}
