package socket;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class ClientClock extends JFrame {
    private JTextField clock;
    private Timer timer;

    public ClientClock() {
        clock = new JTextField(30);
        add(clock, BorderLayout.NORTH);
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 5000);
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    out.writeUTF("time");
                    String response = in.readUTF();
                    clock.setText(response);
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        timer.start();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientClock();
            }
        });
    }
}