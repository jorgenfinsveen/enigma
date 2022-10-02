package no.ntnu.jorgfi.enigma.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>Analyzation Program</b><p>
 * 
 * Used to analyze one or several sentences, returning
 * information regarding whether the sentence(s) are
 * predicted to be a sentence or a statement, and the
 * amount of words in each sentence.
 * 
 * @version 29.09.22
 * @author gruppe11
 */
public class Analyzer {

    /**
     * Analyse one sentence
     * @param sentence is the sentence to analyze
     * @return list where index 0 contains information 
     * regarding the type of the sentence and index 1
     * contains the number of words.
     */
    public static List<String> analyze(String sentence) {
        String bool = isQuestion(sentence) + "";
        String amount = amountOfWords(sentence) + "";
        return new ArrayList<>(Arrays.asList(bool, amount));
    }


    /**
     * Analyse several sentences
     * @param sentences list containing sentences to analyze
     * @return list containing results from the analyzation of
     * each different sentence.
     */
    public static List<ArrayList<String>> analyzeList(String[] sentences) {
        final ArrayList<ArrayList<String>> results = new ArrayList<>(); 
        for (int i = 0; i < sentences.length; i++) {
            results.add((ArrayList<String>) analyze(sentences[i]));
        }
        return results; 
    }


    /**
     * Checks if the sentence is a question or a statement
     * based on whether the last character is a question-mark
     * or not.
     * @param sentence to check
     * @return <code>if question</code> else <code>false</code>
     */
    private static boolean isQuestion(String sentence) {
        return sentence.charAt(sentence.length() -1) == '?';
    }


    /**
     * Counts the amount of words in a sentence by splitting
     * the sentence by each whitespace into a list and check
     * the size of the list.
     * @param sentence to check
     * @return amount of words in the sentence
     */
    private static int amountOfWords(String sentence) {
        return sentence.split("\\s+").length;
    }
}
