package no.ntnu.jorgfi.enigma.tools;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * <b>File Handling Program</b><p>
 * Program responsible for reading data from a .csv-file.
 * Reads each line in the file and creates a placeholder
 * for it. All placeholders are the data which are returned.
 * 
 * @author gruppe11
 * @version 29.09.22
 */
public class FileHandler {

  private static final Path path = Paths.get("src/main/java/no/ntnu/jorgfi/enigma/database/sentences.csv");
  private static final Random rand = new Random();

  /**
   * Method that returns a table from a .csv file.
   * @return Table table containing the table entries
   */
  public static List<Proxy> getFileContent() throws IllegalArgumentException {

    /* Lists for containing table entries and their corresponding proxies */
    ArrayList<String> lines = new ArrayList<>();
    ArrayList<Proxy> contents = new ArrayList<>();


    /* Reads lines from the CSV file and adds them to the lines list */
    try (BufferedReader reader = Files.newBufferedReader(path)) {
      String line;
      while ( (line = reader.readLine() ) != null ) lines.add(line);  
    } catch (IOException e) {
      /* File does not exist. */
      throw new IllegalArgumentException("File not found");
    }


    /* File is empty. */
    if (lines.isEmpty()) throw new IllegalArgumentException("No content in file");


    /* Creates placeholders for each line */
    for(int i = 0; i < lines.size(); i++) {
      String [] attributes = lines.get(i).split(";");
      contents.add( new Proxy(attributes[0], attributes[1], attributes[2]));
    }

    return contents;
  }


  /**
   * Get a random Proxy from the .csv-file
   * @return a random Proxy
   */
  public static Proxy getRandomProxy() throws IllegalArgumentException {

    ArrayList<Proxy> proxies = (ArrayList<Proxy>) getFileContent();
    return proxies.get(rand.nextInt(proxies.size()));
  }
}