package chatapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class A_chat_server_return implements Runnable {

    Socket sock;
    private Scanner input;
    private PrintWriter out;
    String message = "";

    public A_chat_server_return(Socket x) {
        this.sock = x;
    }

    public void check_connection() throws IOException {
        if (!sock.isConnected()) {

            for (int i = 0; i < A_chat_server.ConnectionArray.size(); i++) {
                if (A_chat_server.ConnectionArray.get(i) == sock) {
                    A_chat_server.ConnectionArray.remove(i);
                }
            }

            for (int i = 0; i < A_chat_server.ConnectionArray.size(); i++) {
                Socket temp_sock = (Socket) A_chat_server.ConnectionArray.get(i);
                PrintWriter temp_out = new PrintWriter(temp_sock.getOutputStream());
                temp_out.println(temp_sock.getLocalAddress().getHostName() + " disconnected");
                temp_out.flush();
                System.out.println(temp_sock.getLocalAddress().getHostName() + "disconnected");
            }
        }
    }

    @Override
    public void run() {
        try {
            try {
                input = new Scanner(sock.getInputStream());
                out = new PrintWriter(sock.getOutputStream());
                while (true) {
                    check_connection();
                    if (!input.hasNext()) {
                        return;
                    }
                    message = input.nextLine();

                    System.out.println("Client said: " + message);

                    for (int i = 0; i < A_chat_server.ConnectionArray.size(); i++) {
                        Socket temp_sock = (Socket) A_chat_server.ConnectionArray.get(i);
                        PrintWriter temp_out = new PrintWriter(temp_sock.getOutputStream());
                        temp_out.println(message);
                        temp_out.flush();
                        System.out.println("Sent to: " + temp_sock.getLocalAddress().getHostName());
                    }
                }

            } 
            finally
            {
              sock.close();   
            }
        } catch (Exception ex) {
               System.out.println(ex);
        }
    }

}
