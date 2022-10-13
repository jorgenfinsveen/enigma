package no.ntnu.jorgfi.enigma.app.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

import com.diogonunes.jcolor.Attribute;

import no.ntnu.jorgfi.enigma.lib.Address;
import no.ntnu.jorgfi.enigma.lib.Message;
import no.ntnu.jorgfi.enigma.lib.Port;
import no.ntnu.jorgfi.enigma.lib.Util;

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

    /**
     * Starting the UDP client, which will send the
     * paramter message to the UDP server as a datagram
     * and return the server response to the terminal.
     * @param message to send to the the UDP Server
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

        while (true) {
            try{
                /* Request user-input from STDIN */
                String message = requestCommand();

                /* Send the message to the UDP server */
                sendPacket(message);

                /* Receive a datagram from the ServerUDP */
                String response = receivePacket();

                /* Handle the reponse received from the ServerUDP */
                handleResponse(response);

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
        /* Reads input from STDIN */
        Util.printer(
            Message.CL_MESSAGE_REQUEST,
            false,
            Util.CLIENT_COLOR
        );
        String message = Util.TERMINAL.nextLine();

        /* Translates the message */
        message = translateInput(message);

        /* Checks if the client should disconnect */
        checkDisconnect(message);

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
     */
    private static void handleResponse(String response) {
        /* Translates the response */
        response = translateOutput(response);

        /* Displaying the response to STDOUT part 1 */
        Util.printer(
                Message.C_RESPONSE,
                false,
                Util.SERVER_COLOR
        );

        final Attribute COLOR;

        /* Picking a appropriate color for the response */
        switch (response) {
            case Message.S_OUT_INVALID:
                COLOR = Attribute.TEXT_COLOR(208);
                break;
            case Message.S_OUT_OK:
                COLOR = Attribute.GREEN_TEXT();
                break;
            case Message.S_OUT_ERROR:
                COLOR = Attribute.RED_TEXT();
                break;
            default:
                COLOR = Attribute.NONE();
        }

        /* Displaying the response to STDOUT part 2 */
        Util.printer(response + "\n", true, COLOR);
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
}
