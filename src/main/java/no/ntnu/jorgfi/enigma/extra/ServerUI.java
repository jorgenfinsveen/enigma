package no.ntnu.jorgfi.enigma.extra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public static void main(String[] args) {
        Boolean requested = false;
        System.out.println("\nWELCOME TO SERVER UI");
        System.out.println("\n" + LINES);
        System.out.println("Enter the initializing command to continue...");

        while (!requested) {
            String input = scanner.nextLine();
            if ("task".equals(input)) {
                requested = true;
                showInstructions();
                System.out.println("Write the desired task and press enter");
                start(true);
            }
            else if ("disconnect".equals(input)) {
                System.out.println("\nDisconnecting...");
                scanner.close();
                break;
            }
        }
    }

    
    /**
     * Starts listening for a specified task, and passes it in
     * appropriate format to the reach() method.
     * @param running <code>true</code> until the client disconnects
     */
    public static void start(Boolean running) {  
        System.out.println("");
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
                start(true);
                break;

            // Client requests multiple analyzations
            case "analyze several sentences":
                analyzeSeveralSentences();
                start(true);
                break;

            // Client wants to disconnect
            case "disconnect":
                System.out.println("\nDisconnecting...");
                scanner.close();
                start(false);
                break;

            // Unknown request
            default:
                System.out.println("\n" + LINES);
                System.out.println("The task, \"" + task + "\", is not valid.");
                showInstructions();
                start(true);
        }
    }


    /**
     * Listens for the sentence given by the client.
     * Will run the sentence through the Analyzer-program,
     * and send it to the printResult() formatter.
     */
    private static void analyzeOneSentence() {
        System.out.println("\nSentence: ");
        System.out.println("");
        String input = scanner.nextLine();
        System.out.println("\n" + LINES);
        printResult(Analyzer.analyze(input));
    }

    
    /**
     * Listens for first the amount of sentences which
     * are to be analyzed followed by the subject sentences.
     * All valid sentences will be runned through the 
     * Analyzer-program, and sent to the printResults()
     * formatter.
     */
    private static void analyzeSeveralSentences() {
        System.out.println("\nAmount of sentences: ");
        int amount = scanner.nextInt();
        int index = 0;
        String[] sentences = new String[amount];
        String input;
        System.out.println("\nSentences (Separate using enter): ");
        
        for (int i = 0; i <= amount; i++) {
            input = scanner.nextLine();
            // Sometimes, the scanner unintentionally register blank lines
            if (!input.isEmpty()) {
                sentences[index] = input;
                index++;
            }
            scanner.reset();
        }
        System.out.println("\n" + LINES);
        printResults(Analyzer.analyzeList(sentences));
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
        System.out.println("\nThe available services are:");
        System.out.println("  - analyze one sentence");
        System.out.println("  - analyze several sentences");
        System.out.println("  - disconnect");
        System.out.println(LINES + "\n");
    }
}
