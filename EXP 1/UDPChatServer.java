import java.net.*;
public class UDPChatServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Create a DatagramSocket to listen on port 2000
            socket = new DatagramSocket(2000);
            System.out.println("UDP Server is listening on port 2000...");
            
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            while (true) {
                // Receive a packet from a client
                socket.receive(packet);
                String clientMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Client: " + clientMessage);
                
                // Check for termination message
                if (clientMessage.equalsIgnoreCase("BYE")) {
                    System.out.println("Server shutting down.");
                    break;
                }
                
                // Echo the received message back to the client
                byte[] responseData = clientMessage.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}