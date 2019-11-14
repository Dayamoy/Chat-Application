
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Soc_Server {

    public static void main(String[] args) throws Exception {
        Soc_Server server = new Soc_Server();
        server.run();
    }

    public void run() throws Exception {
        System.out.println("Waiting for Clients.....");
        ServerSocket srvsock = new ServerSocket(1112);
        Socket Sock = srvsock.accept();
        System.out.println("Connected");

        while (true) {
            InputStreamReader Ir = new InputStreamReader(Sock.getInputStream());
            BufferedReader Br = new BufferedReader(Ir);
           

            String Msg = Br.readLine();
            System.out.println(Msg);
            if("Client: bye".equals(Msg)) break;
            
            System.out.print("Server: ");

            PrintStream Ps = new PrintStream(Sock.getOutputStream());
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            //System.out.println(str);
            Ps.println("Server: " + str);
            if("bye".equals(str)) break;
            
            
            Ps.flush();
            
        }

    }
}
