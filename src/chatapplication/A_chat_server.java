package chatapplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class A_chat_server {
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    public static ArrayList<String> CurrentUser = new ArrayList<String>();
    
    public static void main(String[] args) throws IOException
    {
        try
        {
            final int port = 444;
            ServerSocket SERVER = new ServerSocket(port);
            System.out.println("Waiting for client....");
            while(true)
            {
                Socket SOCK = SERVER.accept();
                ConnectionArray.add(SOCK);
                System.out.println("Client connected from " + SOCK.getLocalAddress().getHostName());
                AddUserName(SOCK);
                
                A_chat_server_return CHAT = new A_chat_server_return(SOCK);
                Thread X = new Thread(CHAT);
                X.start();
            }
        }
        catch(Exception e) 
        {
            System.out.println(e);
        }
    }
    
    public static void AddUserName(Socket X) throws IOException
    {
        Scanner INPUT = new Scanner(X.getInputStream());
        String UserName = INPUT.nextLine();
        CurrentUser.add(UserName);
        
        for(int i = 0; i<A_chat_server.ConnectionArray.size(); i++)
        {
            Socket temp_sock = (Socket) A_chat_server.ConnectionArray.get(i);
            PrintWriter out = new PrintWriter(temp_sock.getOutputStream());
            out.println("#?!" + CurrentUser);
            out.flush();
        }
    }
            
}
