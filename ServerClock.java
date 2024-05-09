package socket;

import java.io.*;
import java.net.*;
import java.util.Date;

public class ServerClock {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String request = in.readUTF();
            if ("time".equals(request)) {
                out.writeUTF(new Date().toString());
            }
            socket.close();
        }
    }
}
