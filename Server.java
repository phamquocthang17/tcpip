package socket;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is running...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Thread clientThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        String clientMessage;
                        while ((clientMessage = clientReader.readLine()) != null) {
                            System.out.println("Client: " + clientMessage);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            clientThread.start();

            String serverMessage;
            while ((serverMessage = serverReader.readLine()) != null) {
                out.println(serverMessage);
            }

            socket.close();
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
