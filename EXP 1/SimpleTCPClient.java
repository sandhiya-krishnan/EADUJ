import java.net.*;
import java.io.*;

public class SimpleTCPClient {
    public static void main(String[] args) {
        try {
            // Connect to server at localhost on port 2000
            Socket socket = new Socket("127.0.0.1", 2000);
           
            // Set up input and output streams
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
           
            String message;
            String response;
           
            // Continuously send messages to the server and print the echoed response
            while (true) {
                System.out.print("Client: ");
                message = userInput.readLine();
               
                // Exit the loop if the user types "exit"
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
               
                outputToServer.println(message);
                response = inputFromServer.readLine();
                System.out.println("Server: " + response);
            }
           
            // Close resources
            socket.close();
            inputFromServer.close();
            outputToServer.close();
            userInput.close();
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
