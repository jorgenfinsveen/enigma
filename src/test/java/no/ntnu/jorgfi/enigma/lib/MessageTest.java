package no.ntnu.jorgfi.enigma.lib;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MessageTest {

    public static void setLanguageToNorwegain() {
        Message.lang = "norwegian";
        Message.refresh();
    }

    public static void setLanguageToEnglish() {
        Message.lang = "english";
        Message.refresh();
    }
    
    @Test
    public void testTranslateInput() {
        setLanguageToNorwegain();

        String no = "oppgave";
        String en = "task";

        System.out.println("\nTranslating norwegian input (Language: Norwegian)");
        System.out.println("-----------------------------");
        System.out.println("Before: " + no);
        System.out.println("After: " + Message.translateInput(no));

        assertEquals(en, Message.translateInput(no));
    }

    @Test
    public void testTranslateOutput() {
        setLanguageToNorwegain();

        String no = "oppgave";
        String en = "task";

        System.out.println("\nTranslating english output (Language: Norwegian)");
        System.out.println("-----------------------------");
        System.out.println("Before: " + no);
        System.out.println("After: " + Message.translateOutput(en));

        assertEquals(no, Message.translateOutput(en));
    }

    @Test
    public void testDoesNotTranslateWhenLanguageIsEnglish() {
        setLanguageToEnglish();

        String no = "oppgave";
        String en = "task";

        System.out.println("\nDoes not translate norwegian in/output (Language: English)");
        System.out.println("-----------------------------");
        System.out.println("Before: " + no);
        System.out.println("After: " + Message.translateOutput(en));

        assertEquals(no, Message.translateInput(no));
        assertEquals(en, Message.translateOutput(en));
    }

    @Test
    public void testDoesNotTranslateInvalidStringWhenLanguageIsEnglish() {
        setLanguageToEnglish();

        String no = "aufgaben";
        String en = "operation";

        System.out.println("\nDoes not translate invalid english in/output (Language: English)");
        System.out.println("-----------------------------");
        System.out.println("Before: " + en);
        System.out.println("After: " + Message.translateOutput(en));

        assertEquals(no, Message.translateInput(no));
        assertEquals(en, Message.translateOutput(en));
    }

    @Test
    public void testDoesNotTranslateInvalidStringWhenLanguageIsNorwegian() {
        setLanguageToNorwegain();

        String no = "aufgaben";
        String en = "operation";

        System.out.println("\nDoes not translate invalid norwegain in/output (Language: Norwegain)");
        System.out.println("-----------------------------");
        System.out.println("Before: " + no);
        System.out.println("After: " + Message.translateOutput(no));

        assertEquals(no, Message.translateInput(no));
        assertEquals(en, Message.translateOutput(en));
    }

    @Test
    public void testSplitting() {
        String msg = "operation translate";
        String cmsg = "operation translate";

        String[] split = msg.split("\\s+");

        assertEquals("operation", split[0]);
        assertEquals("translate", split[1]);
        assertEquals(cmsg, msg);
        assertEquals(cmsg, split[0] + " " + split[1]);
    }
}
