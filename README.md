# Enigma project by group 11 

### Main project files:

* ClientUI - Represents the client application as a terminal interface
* TaskServer - Represents the server which the client communicates with through UDP
* Analyzer - Business model offering the services of the server

#### Extra files:

* ServerUI - A server interface where the client is replaced by the user communicating directly through a server which again utilizes the functionality of the Analyzer program
* AnalyserTest - JUnit-tests for the Analyzer. Other parts of the project is not tested since the group agreed that testing through user-testing should be sufficient for this application.


### Protocol assumptions

The group has assumed these simplyfied steps from the protocol described in the GitHub repository:

1. ClientUI will initialize the course of the program by sending phrase "task" as a detatagram. This will be represented by the ClientUI will execute a method-call in TaskServer with the string parameter "task".
2. TaskServer will respond to this by sending a simple sentence. This sentence could be picked at random from a collection in TaskServer, or gathered from a set of sentences in a .csv-file.
3. ClientUI are to process this sentence and detetermine whether the sentence is a question or a statement, and how many words the sentence consists of.
4. ClientUI will forward the results back to TaskServer in a datagram on the following format 	`<type><space><count>, where:`
   1. `<type> - string describing what type of sentence it is. It is either:`
      1. "question"
      2. "statement"
   2. `<count> - integer representing the amount of words in the sentence`
   3. `<space> - one single whitespace character`
5. TaskUI will controll the data from ClientUI and compare the results to the correct answers which it has stored in a collection, responding with "ok" if the answer from ClientUI are correct, or "error" if they are incorrect.
