package no.ntnu.jorgfi.enigma.tools;

/**
 * <b>A proxy for an entry in the .csv-file.</b><p>
 * Intended to work as a placeholder for a line
 * consisting of:<br>
 * 
 * 1. Sentence as a String
 * 2. Type as a String
 * 3. Count as an int
 * 
 * Using this solution, the FileHandler can store all
 * file-entries as an instance of Proxy, which makes
 * the task of reading the file easier and results in
 * more organized return data which will be easier to
 * handle by e.g. the Analyzer. It will also allow for
 * the application manipulating the data without having
 * direct access to the file, leaving them untouched.
 * 
 * @author gruppe11
 * @version 29.09.22
 */
public class Proxy {
    
    // Fields for each of the attributes in a file-entry
    private String sentence;
    private String type;
    private int count;

    /**
     * Create a placeholder for a line in a .csv-file
     * @param sentence first part of a line
     * @param type middle part of a line 
     * @param count last part of a line
     */
    public Proxy(String sentence, String type, String count) {
        this.sentence = sentence;
        this.type = type;
        this.count = Integer.parseInt(count);
    }

    /*
     * Accessors for each of the attributes in a file-entry
     */
    public String getSentence() {return this.sentence;}
    public String getType() {return this.type;}
    public int getCount() {return this.count;}
}
