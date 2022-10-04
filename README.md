# Enigma project by group 11

### Main project:

* Client-Server UDP connection.
* Follows the steps defined in "Protocol assumptions" further down in this file.
* Essential directories:
  * core: Contains Main.java and the launcher-code for both the server and the client.
  * app: Contains Client.java and Server.java, which are the representations of the client and the server.
  * tools: Contains FileHandler.java and Proxy.java, which are both used to read the file containing test-sentences.
  * database: Contains .csv-files containing test-sentences.

#### Extra files:

* In addition to the main project described in the assignment. This project includes some extra functionality. This includes:
  * Multi language support - Allows for the program to either run in english, or in norwegian. This also affects the test-sentences displayed, and the name of the commands which the user can use.
  * Colorized terminal - Colorizes outputs in the terminal, making it more decorative and more readable.
  * ServerUI-mode - A non-UDP solution where the user communicates directly to a server through the terminal.
  * Server configuration - Allows for the usage of an external server instead of the server which is located in this project.
  * Full-auto - A mode where a the user does not need to interact at all. The client will use an analyzer-program to analyze all sentences which the server sends it.
* All this functionality can be activated in the file named Config.java, which is located in "/src/main/java/config".

### Protocol assumptions

The group has assumed these simplyfied steps from the protocol described in the GitHub repository:

1. The program gets initialized by the Main-class, which again will launh the client and the server.
2. The user will receive a task by the server when using the client to send the phrase "task" as a detatagram to the server.
3. The task is simple sentence picked at random from a collection in a .csv-file.
4. The user have to process this sentence and detetermine whether the sentence is a question or a statement, and how many words the sentence consists of.
5. The client will forward the answers from the user back to the server in a datagram of the following format  `<type> <count>, where:`
   1. `<type> - string describing the type of the sentence. Valid parameters are:`
      1. "question" - Indicating that the sentence is a question
      2. "statement" - Indicating that the sentence is a statement
   2. `<count> - integer representing the amount of words in the sentence`
6. The server will controll the answers received from the client and compare the results to the correct answers which is stored along with the sentence gathered from the .csv-file.
7. The server will respond by either the string "ok" if the answers are correct, or "error" if they are not.
8. The user can at any time disconnect and exit the program by entering the phrase "disconnect".

### Configurating the application

* The program can be configured through "/src/main/java/config/Config.java"
* The configurations available:
  * Server configuration - SERVER_CONFIG( )
  * Mode configuration - MODE_CONFIG( )
  * Language configuration - LANGUAGE_CONFIG( )
  * Color configuration - COLOR_CONFIG( )
  * Full-auto configuration
* To use a custom configuration instead of the default configuration, change the parameters in the corresponding methods defined in Config.java.
* Valid parameters for each configuration is defined in their corresponding methods JavaDoc.
