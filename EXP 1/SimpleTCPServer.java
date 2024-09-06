import java.net.*;
import java.io.*;
public class SimpleTCPServer {
    public static void main(String[] args) {
        try {
            // Create a server socket on port 2000
            ServerSocket serverSocket = new ServerSocket(2000);
            System.out.println("Server is listening on port 2000...");
           
            // Wait for a client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");
           
            // Set up input and output streams
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);
           
            String clientMessage;
           
            // Continuously read and echo messages from the client
            while ((clientMessage = inputFromClient.readLine()) != null) {
                System.out.println("Client: " + clientMessage);
                outputToClient.println("Echo: " + clientMessage);
            }
           
            // Close resources
            clientSocket.close();
            serverSocket.close();
            inputFromClient.close();
            outputToClient.close();
           
            System.out.println("Server closed.");
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}