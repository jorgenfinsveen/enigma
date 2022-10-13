package no.ntnu.jorgfi.enigma.app.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.List;
import java.util.Scanner;

import com.diogonunes.jcolor.Attribute;

import no.ntnu.jorgfi.enigma.core.build.Build;
import no.ntnu.jorgfi.enigma.lib.Address;
import no.ntnu.jorgfi.enigma.lib.Message;
import no.ntnu.jorgfi.enigma.lib.Port;
import no.ntnu.jorgfi.enigma.lib.Util;
import no.ntnu.jorgfi.enigma.model.Analyzer;

/**
 * <b>UDP Client<b><p>
 * 
 * The client will take a string passed as a parameter,
 * convert it to binary format, and send it to the UDP
 * Server.
 * After, it will wait for the server to respond. When
 * receiving a response, the response will be converted 
 * to a string and be printed to STDOUT.
 * 
 * @author gruppe11
 * @version 30.09.22
 */
public class ClientUDP {

    /** Used at Full-Auto for keeping track of tasks answered */
    private static int taskCount = 0;
    private static int correct = 0;
    private static int wrong = 0;

     /**
     * Thread listening for input from STDIN when using
     * full auto. It should then stop the program.
     */
    private static final Thread LISTENER = new Thread() {
        @Override
        public void run() { 
            System.out.println("started");
            int i;
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("in while");
                try {
                    i = scanner.nextInt();
                    break;
                } catch (Exception e) {System.out.println("none");}
            }
            System.exit(i);
        }
    };

    /**
     * Starting the UDP client, which will send the
     * paramter message to the UDP server as a datagram
     * and return the server response to the terminal.
     * @param message to send to the the UDP Server
     */
    public void run() {

       if (Build.FULL_AUTO) {LISTENER.start();}

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

        while (true) {
            try{
                if ((Build.FULL_AUTO && taskCount % 2 == 0)
                    || !Build.FULL_AUTO) {

                    /* Request user-input from STDIN */
                    String message = requestCommand(); 

                    taskCount++;

                    /* Send the message to the UDP server */
                    sendPacket(message); 
                } 


                if ((Build.FULL_AUTO && taskCount % 2 != 0)
                || !Build.FULL_AUTO) {

                    /* Receive a datagram from the ServerUDP */
                    String response = receivePacket();

                    taskCount++;

                    /* Handle the reponse received from the ServerUDP */
                    handleResponse(response);
                }

                if (Build.FULL_AUTO) Thread.sleep(1000);

            } catch (Exception e) { throwException(e);}
        }
    }




    /**
     * Translates inputs to english from the specified
     * application language.
     * @param message to be translated
     * @return translated message
     */
    private static String translateInput(String message) {
        String[] splitted = message.split("\\s+");
        if (splitted.length > 1) {
            String m1 = splitted[0].trim();
            String m2 = splitted[1];
            message = Message.translateInput(m1) + " " + m2;
        } else {
            message = Message.translateInput(message);
            message = message.trim();
        }
        return message;
    }




    /**
     * Translates inputs to english from the specified
     * application language.
     * @param message to be translated
     * @return translated message
     */
    private static String translateOutput(String message) {
        String[] splitted = message.split("\\s+");
        /* 
         * If the output contains more than three words, it is 
         * a task-sentence which should not be translated.
        */
        if (splitted.length > 1 && splitted.length < 3) {
            String newMessage = "";
            String m1;
            for (int i = 0; i < splitted.length; i++) {
                m1 = splitted[i].trim();
                newMessage += Message.translateInput(m1) + " ";
            }
            message = newMessage.substring(0, newMessage.length()-1);
        } else {
            message = Message.translateInput(message);
            message = message.trim();
        }
        return message;
    }



    /**
     * Receive a DatagramPacket from a server.
     * 
     * @throws IOException
     *      Socket cannot receive a DatagramPacket.
     */
    private static String receivePacket() throws IOException {
        /* Creating a binary sequence for the response with a fitting size*/
        byte[] message = new byte[1024];

        /* Creating a variable to hold the packet to be recieved */
        DatagramPacket receivingPacket = new DatagramPacket (
            message,
            message.length
        );

        /* Receive the datagram from ServerUDP */
        Util.CLIENT_SOCKET.receive(receivingPacket);

        /* Converting the datagram to a readable String */
        return new String (
            receivingPacket.getData(),
            0,
            receivingPacket.getLength()
        );
    }



    /**
     * Asks the user for a command which can be sent to the server.
     * 
     * @return message from user.
     */
    private static String requestCommand() {

        String message;

        if (!Build.FULL_AUTO) {
            /* Reads input from STDIN */
            Util.printer(
                Message.CL_MESSAGE_REQUEST,
                false,
                Util.CLIENT_COLOR
            );
            message = Util.TERMINAL.nextLine();

            /* Translates the message */
            message = translateInput(message);

            /* Checks if the client should disconnect */
            checkDisconnect(message);
        } else message = translateInput(Message.C_IN_TASK);
        return message;
    }



    /**
     * Checks a if the command received from the user is
     * a disconnect command.
     * 
     * @param message translated input from the user.
     */
    private static void checkDisconnect(String message) {
        /* If the user requested disconnect, the application stops */
        if (Message.C_IN_DISCONNECT.equalsIgnoreCase(message)) {
            Util.printer(
                Message.C,
                false,
                Util.CLIENT_COLOR
            );

            Util.printer(
                Message.EXIT_MESSAGE,
                true,
                Util.EXIT_COLOR
            );
            Util.CLIENT_SOCKET.close();
            System.exit(0);
        }
    }



    /**
     * Sends a command from the user as a DatagramPacket to
     * the specified server.
     * 
     * @param message to send.
     */
    private static void sendPacket(String message) throws IOException {
        /* Converts the message into a binary sequence */
        byte[] binaryMessage = message.getBytes();

        DatagramPacket sendingPacket = new DatagramPacket (
            binaryMessage, 
            binaryMessage.length,
            InetAddress.getByName(Address.ACTIVE_HOST),
            Port.ACTIVE_PORT 
        );
 
        /* Uses the socket to send the datagram to the ServerUDP */
        Util.CLIENT_SOCKET.send(sendingPacket);
    }



    /**
     * Handle the content from the received DatagramPacket
     * and formats the user-ouput.
     * 
     * @param response to handle.
     * @throws IOException
     */
    private static void handleResponse(String response) throws IOException {
        /* Translates the response */
        response = translateOutput(response);

        final Attribute COLOR;
        boolean isTask = false;

        /* Picking a appropriate color for the response */
        switch (response) {
            case Message.S_OUT_INVALID:
                COLOR = Attribute.TEXT_COLOR(208);
                isTask = false;
                break;
            case Message.S_OUT_OK:
                COLOR = Attribute.GREEN_TEXT();
                isTask = false;
                correct = correct + 1;
                break;
            case Message.S_OUT_ERROR:
                COLOR = Attribute.RED_TEXT();
                isTask = false;
                wrong = wrong + 1;
                break;
            default:
                COLOR = Attribute.NONE();
                isTask = true;
        }

        if (Build.FULL_AUTO && isTask) {
            System.out.println(); 
            taskCount++;
        }

        /* Displaying the response to STDOUT part 1 */
        Util.printer(
                Message.C_RESPONSE,
                false,
                Util.SERVER_COLOR
        );

        /* Displaying the response to STDOUT part 2 */
        Util.printer(response, true, COLOR);
        if (Build.FULL_AUTO && isTask) autoAnalyze(response);
        if (!Build.FULL_AUTO && !isTask) System.out.println();
    }



    /**
     * Throw an exception if the Client-Server communication
     * is damaged.
     * 
     * @param e the exception which occurred.
     */
    private static void throwException(Exception e) {
        /* Prints error message to STDOUT */
        Util.errorPrinter(Message.C_ERROR, true);
        Util.CLIENT_SOCKET.close();
        e.printStackTrace();
        System.exit(1);
        throw new RuntimeException(e);
    }




    /**
     * @throws IOException
     * 
     */
    private static void autoAnalyze(String response) throws IOException {

        response = response.trim();

        List<String> result = Analyzer.analyze(response);

        boolean isQuestion = Boolean.parseBoolean(result.get(0));
        int count = Integer.parseInt(result.get(1));

        String m1 = (
            (isQuestion) ? Message.C_IN_QUESTION : Message.C_IN_STATEMENT
        );

        String solution = translateInput(m1 + " " + count).trim();
        Util.printer("Analyzer: ", false, Util.ANALYZER_COLOR);
        Util.printer(solution, true, Attribute.NONE());
        sendPacket(translateInput(m1 + " " + count));
    }
}