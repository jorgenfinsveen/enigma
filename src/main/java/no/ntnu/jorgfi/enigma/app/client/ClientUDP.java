package no.ntnu.jorgfi.enigma.app.client;

import java.net.*;

import com.diogonunes.jcolor.Attribute;

import no.ntnu.jorgfi.enigma.lib.*;

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
    public void run(String message) {

        try {

            /* Creates a new socket for sending datagrams */
            DatagramSocket socket = new DatagramSocket();

            /* Converts the message into a binary sequence */
            byte[] binaryMessage = message.getBytes();

            DatagramPacket sendingPacket = new DatagramPacket (
                binaryMessage, 
                binaryMessage.length,
                InetAddress.getByName(Address.LOCALHOST),
                Port.LOCALHOST_RPC 
            );

            /* Uses the socket to send the datagram to the ServerUDP */
            socket.send(sendingPacket);


            /* Creating a binary sequence for the response with a fitting size*/
            byte[] binaryResponse = new byte[1024];

            /* Creating a variable to hold the packet to be recieved */
            DatagramPacket receivingPacket = new DatagramPacket (
                binaryResponse,
                binaryResponse.length
            );

            /* Receive the datagram from ServerUDP */
            socket.receive(receivingPacket);

            /* Closing the socket and releasing the memory allocated to it */
            socket.close();


            /* Converting the datagram to a readable String */
            String response = new String (
                receivingPacket.getData(),
                0,
                receivingPacket.getLength()
            );

            /* Displaying the response to STDOUT part 1 */
            Util.printer(
                    Message.C_RESPONSE,
                    false,
                    Util.SERVER_COLOR
            );

            final Attribute COLOR;

            /* Picking a appropriate color for the response */
            switch (response) {
                case "Invalid request":
                    COLOR = Attribute.TEXT_COLOR(208);
                    break;
                case "ok":
                    COLOR = Attribute.GREEN_TEXT();
                    break;
                case "error":
                    COLOR = Attribute.RED_TEXT();
                    break;
                default:
                    COLOR = Attribute.NONE();
            }

            /* Displaying the response to STDOUT part 2 */
            Util.printer(response + "\n", true, COLOR);
            

        } catch (Exception e) {

            /* Prints error message to STDOUT */
            Util.errorPrinter(Message.C_ERROR, true);
            throw new RuntimeException(e);
        }
    }
}
