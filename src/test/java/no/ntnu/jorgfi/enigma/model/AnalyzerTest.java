package no.ntnu.jorgfi.enigma.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * <b>Unit test for Analyzer</b><p>
 * 
 * Runs tests on the analyzer to ensure that the code
 * is working properly. There are no tests for 
 * <code>ServerUI</code>, as the group found it more natural
 * to test it through executing the application directly.
 * 
 * @version 29.09.22
 * @author gruppe11
 */
public class AnalyzerTest {
    
    /**
     * A collection of sentences which can be used to
     * test the analyzer with.
     */
    private String[] testSentences = {
        "How are you today?",
        "I am fine, thank you.",
        "How is your day?",
        "My day is fine",
        "What did you do today?",
        "I overdosed on fentanyl plasters!",
        "Can I have some?",
        "No.",
        "Why?",
        "I need it to cope with my severe opioid addiction!",
    };

    /**
     * A collection of stringed booleans corresponding to
     * the sentences in <code>String[] testSentences</code> 
     * when determining whether it is a question or not.
     */
    private String[] testTypes = {
        "true", "false","true", "false","true",
        "false","true", "false", "true", "false",
    };

    /**
     * A collection of stringed integers corresponding to
     * the sentences in <code>String[] testSentences</code> 
     * when determining its amount of words.
     */
    private String[] testAmounts = {
        "4", "5", "4", "4", "5", "5", "4", "1", "1", "10"
    };


    /**
     * Passes if the given strings passed as a parameter in the
     * analyze() method gives the correct results.
     */
    @Test
    public void analyseOneSentence_postive() {
        assertEquals("true", Analyzer.analyze(testSentences[0]).get(0));
        assertEquals("4", Analyzer.analyze(testSentences[0]).get(1));

        assertEquals("false", Analyzer.analyze(testSentences[1]).get(0));
        assertEquals("5", Analyzer.analyze(testSentences[1]).get(1));
    }


    /**
     * Passes if the given strings passed as a parameter in the
     * analyze() method rejects the incorrect results.
     */
    @Test
    public void analyseOneSentence_negative() {
        assertNotEquals("false", Analyzer.analyze(testSentences[0]).get(0));
        assertNotEquals("2", Analyzer.analyze(testSentences[0]).get(1));

        assertNotEquals("true", Analyzer.analyze(testSentences[1]).get(0));
        assertNotEquals("2", Analyzer.analyze(testSentences[1]).get(1));
    }


    /**
     * Passes if all strings passed as a parameter in the
     * analyzeList() method gives the correct results.
     */
    @Test
    public void analyzeSeveralSentences_positive() {

        List<ArrayList<String>> results = Analyzer.analyzeList(testSentences);

        for (int i = 0; i < testSentences.length; i++) {
            assertEquals(testTypes[i], results.get(i).get(0));
            assertEquals(testAmounts[i], results.get(i).get(1));
        }
    }


    /**
     * Passes if all strings passed as a parameter in the
     * analyzeList() method rejects incorrect results.
     */
    @Test
    public void analyzeSeveralSentences_negative() {

        List<ArrayList<String>> results = Analyzer.analyzeList(testSentences);

        for (int i = 0; i < testSentences.length; i++) {
            assertNotEquals("wow", results.get(i).get(0));
            assertNotEquals("-1", results.get(i).get(1));
        }   
    }

    @Test
    public void testZeroLengthSentences() {
        String s1 = ".";
        String s2 = "?";

        assertEquals("false", Analyzer.analyze(s1).get(0));
        assertEquals("0", Analyzer.analyze(s1).get(1));

        assertEquals("true", Analyzer.analyze(s2).get(0));
        assertEquals("0", Analyzer.analyze(s2).get(1));
    }
}