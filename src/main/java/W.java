import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mareckip on 03.06.16.
 */
public class W {
    public static void main(String[] args) throws Exception {
            InetAddress addr = InetAddress.getByName("localhost");
            ServerSocket ss = new ServerSocket(22222, 50, addr);
            Socket s = ss.accept();
            System.out.println("połączenie nawiązano...");
            InputStream is = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while((line=br.readLine())!=null) {
                if ("ex".equals(line)) break;
                System.out.println("-> " + line);
            }
            s.close();
    }
}
