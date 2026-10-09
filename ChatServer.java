import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        int port = 5000;
        System.out.println("Server is starting and listening on port " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Client connected! Start chatting.");

            String clientMessage, serverMessage;

            while (true) {
                // 1. Read message from the client
                if ((clientMessage = in.readLine()) != null) {
                    System.out.println("Client: " + clientMessage);
                    if (clientMessage.equalsIgnoreCase("bye")) {
                        System.out.println("Client disconnected.");
                        break;
                    }
                }

                // 2. Send a reply from the server console
                System.out.print("Server (You): ");
                serverMessage = consoleIn.readLine();
                out.println(serverMessage);
               
                if (serverMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Closing connection...");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}

