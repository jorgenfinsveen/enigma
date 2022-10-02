# Start Directory

Point of initialization of the project. All files within this directory contains a main(String[] args) method which will automatically be executed upon running the project.

### Sub-Directories

* Client:
  * Contains: ClientLauncher.java
  * Intention: ClientLauncher will read user input from the console, and create an instance of the ClientUDP program upon successfull launching-process. ClientLauncher will then execute a method-call to ClientUDP.run(String message)
* Server:
  * Contains: ServerLauncher.java
  * Intention: ServerLauncher will create an instance of the ServerUDP program and execute a method-call to ServerUDP.run( )
