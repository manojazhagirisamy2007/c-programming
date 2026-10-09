import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        // CHANGE THIS: Replace with your Server PC's actual local IP address
        String host = "192.168.17.192";

        int port = 5000;
        System.out.println("Connecting to server at " + host + ":" + port + "...");

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server successfully! Start chatting.");

            String clientMessage, serverMessage;

            while (true) {
                // 1. Send a message to the server
                System.out.print("Client (You): ");
                clientMessage = consoleIn.readLine();
                out.println(clientMessage);

                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Closing connection...");
                    break;
                }

                // 2. Read the reply from the server
                if ((serverMessage = in.readLine()) != null) {
                    System.out.println("Server: " + serverMessage);
                    if (serverMessage.equalsIgnoreCase("bye")) {
                        System.out.println("Server closed the connection.");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
        }
    }
}

