import java.net.*;
import java.io.*;

public class UDPChatClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        BufferedReader userInput = null;
        
        try {
            // Create a DatagramSocket to send and receive packets
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1"); // Server IP
            int serverPort = 2000; // Server port
            
            userInput = new BufferedReader(new InputStreamReader(System.in));
            
            while (true) {
                // Read input from user
                System.out.print("Client: ");
                String message = userInput.readLine();
                
                // Send message to server
                byte[] messageData = message.getBytes();
                DatagramPacket packet = new DatagramPacket(messageData, messageData.length, serverAddress, serverPort);
                socket.send(packet);
                
                // Check for termination message
                if (message.equalsIgnoreCase("BYE")) {
                    System.out.println("Client exiting.");
                    break;
                }
                
                // Receive response from server
                byte[] buffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);
                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("Server: " + response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            if (userInput != null) {
                try {
                    userInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
