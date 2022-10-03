package no.ntnu.jorgfi.enigma.app.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import no.ntnu.jorgfi.enigma.lib.Address;
import no.ntnu.jorgfi.enigma.lib.Message;
import no.ntnu.jorgfi.enigma.lib.Port;
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
    
    /** True if there is an active task */
    private static boolean taskRequested = false;

    /* Contents of active task */
    private static String taskSentence = "";
    private static String taskType = "";
    private static int taskCount = 0;

    /**
     * Starting the UDP server, which will
     * wait to receive a datagram from ClientUDP.
     * Will return the datagram to the client in
     * uppercase format.
     */
    public void run() {

        
        /* Printing active ip-address to STDOUT */
        Util.printer(
            Message.S_ACTIVE_1 + Address.ACTIVE_HOST,
            true,
            Util.SERVER_COLOR
        );

        /* Printing active port to STDOUT */
        Util.printer(
            Message.S_ACTIVE_2 + Port.ACTIVE_PORT,
            true,
            Util.SERVER_COLOR
        );

        /* Aesthetic spacing */
        System.out.println();

        /* Creating a binary sequence for the upcoming appeal with a fitting size*/
        byte[] binaryAppeal = new byte[1024];

        DatagramSocket socket;
        try {
            socket = new DatagramSocket(Port.ACTIVE_PORT);

        } catch (Exception e) {

            /* Prints error message to STDOUT */
            Util.errorPrinter(Message.S_SOCKET_ERROR, true);
            return;
        }

        /* Runs until the application shuts down */
        while (true) {
            try {
                /* Creating a variable to hold the packet to be recieved */
                DatagramPacket receivingPacket = new DatagramPacket (
                    binaryAppeal,
                    binaryAppeal.length
                );


                /* Receive the datagram from ServerUDP */
                socket.receive(receivingPacket);

                /* Converting the datagram to a readable String */
                String message = new String (
                    receivingPacket.getData(),
                    0,
                    receivingPacket.getLength()
                );

                /* Getting address and port of ClientUDP */
                InetAddress clientAddress = receivingPacket.getAddress();
                int clientPort = receivingPacket.getPort();


                byte[] binaryResponse;


                if (!taskRequested) {
                    if ("task".equals(message)) {
                        Proxy radomTask = FileHandler.getRandomProxy();

                        setTask(
                            radomTask.getSentence(),
                            radomTask.getType(),
                            radomTask.getCount()
                        );

                        binaryResponse = taskSentence.getBytes();
                        taskRequested = true;

                    } else {
                        binaryResponse = "Invalid request".getBytes();
                    }
                } else {
                    String[] content = message.split("\\s+");
                    binaryResponse = "error".getBytes();

                    if (content.length == 2) {
                        String suggestedType = content[0];

                        try {
                            int suggestedCount = Integer.parseInt(content[1]);
                            if (taskType.equalsIgnoreCase(suggestedType) && taskCount == suggestedCount) {
                                binaryResponse = "ok".getBytes();
                                taskRequested = false;
                            } 
                        } catch (Exception e) {}
                    }
                }

                /* Checks if the user wants to disconnect */
                try {
                    if ("disconnect".equalsIgnoreCase(message.substring(0, 10))) {
                        Util.printer(
                            Message.C_RESPONSE,
                            false,
                            Util.SERVER_COLOR
                        );

                        Util.printer(
                            Message.EXIT_MESSAGE,
                            true,
                            Util.EXIT_COLOR
                        );
                        System.exit(0);
                    }
                } catch (Exception e) {}


                DatagramPacket sendingPacket = new DatagramPacket (
                    binaryResponse, 
                    binaryResponse.length,
                    clientAddress,
                    clientPort 
                );

                /* Uses the socket to send the datagram to ClientUDP */
                socket.send(sendingPacket);
            
            } catch (Exception e) {
                
                /* Prints error message to STDOUT */
                Util.errorPrinter(Message.S_COMMUNICATION_ERROR, true);
                socket.close();
                throw new RuntimeException(e);
            }
        }
    }



    /**
     * Saves the current task
     */
    private static void setTask(String sentence, String type, int count) {
        taskSentence = sentence;
        taskType = type;
        taskCount = count;
    }
}
