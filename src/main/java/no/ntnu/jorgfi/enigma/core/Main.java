package no.ntnu.jorgfi.enigma.core;

import no.ntnu.jorgfi.enigma.core.client.ClientLauncher;
import no.ntnu.jorgfi.enigma.core.server.ServerLauncher;


/**
 * <h2>Application Core</h2>
 * <p>
 * When starting the UDP-Application, this is the
 * first program to launch. We discovered that we 
 * needed to launch both the ClientLauncher and
 * the ServerLauncher in parallel for our solution
 * to work, since one launcher will wait for the 
 * other before proceeding, but the second launcher
 * to start will not be launched until the first
 * launcher is completely executed, hence leading
 * to a stalemate.
 * <p>
 * The solution to this was to unite the launching
 * of both the ClientLauncher and the ServerLauncher
 * in one main-method, which runs both the ClientUDP
 * side of the application and the ServerUDP side in 
 * parallel, using multi-threading. Therefore, we 
 * have made one designated thread for all Server
 * operations, and another thread for all Client
 * operations.
 * <p>
 * @author gruppe11
 * @version 02.10.22
 * 
 */
public class Main {

    /** String array args from program init to use in threads */
    private static String[] args;


    /**
    * <h2>First method to launch</h2>
    * <p> 
    * <b>Procedure:</b>
    * <p><ul>
        * <li> <p>Stores the program initialization arguments.</p>
        * <li> <p>Sets thread-priorities so that the server is
        *       running before launching the client.</p>
        * <li> <p>Starts both threads with 0.1 seconds timeout
        *       between the execution of each thread launch.</p>
        * <li> <p>Normalizes the thread priorities after 
        *       initialization.</p> &emsp;
    * </ul></p>
    * @param args
    * @throws InterruptedException if <code>Thread.sleep(100)</code>
    *   fails to stop the thread.
    */
    public static void main(String[] args) 
        throws InterruptedException {

        /* Aesthetic spacing */
        System.out.println();

        /* Store the method-args, which will be reused */
        Main.args = args;

        /* 
         Sets max priority to the server-thread,
         and minimum priority to the client-thread
        */
        SERVER_THREAD.setPriority(Thread.MAX_PRIORITY);
        CLIENT_THREAD.setPriority(Thread.MIN_PRIORITY);

        /* Starts the server thread */
        SERVER_THREAD.start();

        /* 
         Stops the program for 100 milliseconds so that 
         the server-thread gets a head start.
        */
        Thread.sleep(100);

        /* Starts the client thread */
        CLIENT_THREAD.start();   
        
        /* 
         Since the different threads will wait for responses
         from eachother after the ServerUDP and ClientUDP 
         are launched.
        */
        SERVER_THREAD.setPriority(Thread.NORM_PRIORITY);
        CLIENT_THREAD.setPriority(Thread.NORM_PRIORITY);
    }


    /**
     * Thread running the server-part of the application
     * @return thread running ServerLauncher.java
     */
    private static final Thread SERVER_THREAD = new Thread() {
        @Override
        public void run() { ServerLauncher.main(args); }
    };


    /**
     * Thread running running the client-part of the application
     * @return thread running ClientLauncher.java
     */
    private static final Thread CLIENT_THREAD = new Thread() {
        @Override
        public void run() { ClientLauncher.main(args); }
    };
}