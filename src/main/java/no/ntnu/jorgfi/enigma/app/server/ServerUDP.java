package no.ntnu.jorgfi.enigma.app.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;

import no.ntnu.jorgfi.enigma.lib.Message;
import no.ntnu.jorgfi.enigma.lib.Util;
import no.ntnu.jorgfi.enigma.tools.FileHandler;
import no.ntnu.jorgfi.enigma.tools.Proxy;

/**
 * <b>UDP Server<b><p>
 * 
 * The server will wait until it receives a
 * datagram from the UDP client. It will then
 * read the message and change it to uppercase
 * format before sending it back to the client.
 * 
 * @author gruppe11
 * @version 30.09.22
 */
public class ServerUDP {
    
    /* HashMap for sotring clients and the task which they are given */
    private static final HashMap<Integer,Proxy> CLIENTS = new HashMap<>();

    /**
     * Starting the UDP server, which will
     * wait to receive a datagram from ClientUDP.
     * Will return the datagram to the client in
     * uppercase format.
     */
    public void run() {

        /* Runs until the application shuts down */
        while (true) {
            try {
               
                /* Receiving a datagram */
                Object[] data = receivePacket();
                Object[] origin = new Object[] {(InetAddress) data[1], (int) data[2]};
                String message = (String) data[0];


                /* Handles the received message and choosed correct response */
                String response = distribute(origin, message);

                /* Sends response back to the client */
                sendPacket(origin, response);
            
            } catch (Exception e) {throwException(e);}
        }
    }




    /**
     * Receive a DatagramPacket from a client.
     * 
     * @return client information [message, IP, port]
     * @throws IOException
     *      Socket cannot receive a DatagramPacket. 
     */
    private static Object[] receivePacket() throws IOException {
        /* Creating a binary sequence for the upcoming appeal with a fitting size*/
        byte[] bytes = new byte[1024];

         /* Creating a variable to hold the packet to be recieved */
         DatagramPacket packet = new DatagramPacket (
            bytes,
            bytes.length
        );

        /* Receive the datagram from ServerUDP */
        Util.SERVER_SOCKET.receive(packet);

        /* Converting the datagram to a readable String */
        String message = new String (
            packet.getData(),
            0,
            packet.getLength()
        );

        /* Getting address and port of ClientUDP */
        InetAddress clientAddress = packet.getAddress();
        int clientPort = packet.getPort();

        return new Object[] {message, clientAddress, clientPort};
    }




    /**
     * Send a DatagramPacket to a client.
     * 
     * @param message to send.
     * @param address of the client.
     * @param port number which the client is active upon.
     * @throws IOException 
     *      Socket does not send DatagramPacket successfully.
     */
    private static void sendPacket(Object[] origin, String message) throws IOException {

        byte[] bytes = message.getBytes();
        InetAddress address = (InetAddress) origin[0];
        int port = (int) origin[1];

        /* Creating a new datagram to send to a client */
        DatagramPacket sendingPacket = new DatagramPacket (
            bytes, 
            bytes.length,
            address,
            port 
        );

        /* Uses the socket to send the datagram to ClientUDP */
        Util.SERVER_SOCKET.send(sendingPacket);
    }




    /**
     * Distribute the responible operation for a specific
     * type of message recieved from a client.
     */
    private static String distribute(Object[] origin, String message) {
        
        String response;
        int hash = hashOrigin(origin);

        if (!CLIENTS.containsKey(hash)) {
            if (Message.C_IN_TASK.equals(message)) response = taskRequested(origin);
            else {response = Message.S_OUT_INVALID;}
        } else if (Message.C_IN_TASK.equals(message)) {
            CLIENTS.remove(hash);
            response = taskRequested(origin);
        } else response = expectSolution(origin, message);

        return response;
    }




    /**
     * Return a task randomly chosen from the
     * actice question bank.
     * 
     * @return task
     */
    private static String taskRequested(Object[] origin) {
        Proxy task = FileHandler.getRandomProxy();
        CLIENTS.put(hashOrigin(origin), task);
        return task.getSentence();
    }




    /**
     * A client has sent answers to a task sent by the server.
     * 
     * @param message which the client sent.
     * @return appropriate server response.
     */
    private static String expectSolution(Object[] origin, String message) {

        /* Create default response and splits client message on whitespace */
        String response = Message.S_OUT_ERROR;
        String[] content = message.split("\\s+");

        int hash = hashOrigin(origin);

        /* Finds the task given by the server to this particular client */
        Proxy task = CLIENTS.get(hash);
        String type = task.getType();
        int count = task.getCount();

        /* Validates the answers */
        if (content.length == 2) {
            String suggestedType = content[0];
            /* Correct answer implies that content[1] is of type int */
            try {
                int suggestedCount = Integer.parseInt(content[1]);
                if (type.equalsIgnoreCase(suggestedType) && count == suggestedCount) {
                    response = Message.S_OUT_OK;
                    CLIENTS.remove(hash);
                } 
            } catch (NumberFormatException e) {}
        }
        return response;
    }




    /**
     * Logs occuring error, close the socket and throw
     * a RuntimeExceptopn.
     * 
     * @param e the exception to throw.
     */
    private static void throwException(Exception e) {
        /* Prints error message to STDOUT */
        Util.errorPrinter(Message.S_COMMUNICATION_ERROR, true);
        Util.SERVER_SOCKET.close();
        throw new RuntimeException(e);
    }

    /**
     * Hashes an IP-address and port number to
     * be used as a key in CLIENTS HashMap.
     * 
     * @param origin an Object[] where orgin[0]
     *      is the IP-address, and origin[1] is
     *      the port number.
     * @return hash.
     */
    private static int hashOrigin(Object[] origin) {
        int ip = 0;
        InetAddress inet = (InetAddress) origin[0];
        String address = inet.getHostAddress();
        int port = (int) origin[1];
        String[] split = address.split("[.]");
        for (String segment : split) {
            ip += Integer.parseInt(segment);
        }
        int hash = 17;
        hash = hash * 5 + ip;
        hash=hash*5+port;
        return hash;
    }
}
