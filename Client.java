package socket;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Thread serverThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        String serverMessage;
                        while ((serverMessage = serverReader.readLine()) != null) {
                            System.out.println("Server: " + serverMessage);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            serverThread.start();

            String clientMessage;
            while ((clientMessage = clientReader.readLine()) != null) {
                out.println(clientMessage);
            }

            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
