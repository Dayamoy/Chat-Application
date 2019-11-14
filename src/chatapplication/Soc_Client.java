package chatapplication;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Soc_Client {

    public static void main(String[] args) throws Exception {
        Soc_Client server = new Soc_Client();
        server.run();
    }

    public void run() throws Exception {

        System.out.println("Connected to Server.");
        Socket Sock = new Socket("localhost", 1112);
        PrintStream Ps = new PrintStream(Sock.getOutputStream());
        while (true) {
            System.out.print("Client: ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            //System.out.println(str);
            Ps.println("Client: " + str);
            if("bye".equals(str)) break;

            

            InputStreamReader Ir = new InputStreamReader(Sock.getInputStream());
            BufferedReader Br = new BufferedReader(Ir);

            String Msg = Br.readLine();
            System.out.println(Msg);
            if("Server: bye".equals(Msg)) break;
            Ps.flush();
            
            
        }
    }
}
