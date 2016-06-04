import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mareckip on 03.06.16.
 */
public class W_wielokrotny {

    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getByName("localhost");
        //Otwieramy pojedynczy socket serwerowy
        ServerSocket ss = new ServerSocket(22222, 50, addr);
        int count = 0;
        while(true) {
            Socket s = ss.accept(); //nawiązujemy połączenie TCP z klientem
            InputStream is = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while((line=br.readLine())!=null) {
                if ("ex".equals(line)) break;
//                System.out.println("-> " + line);
            }
            count++;
            s.close();  //zamykamy połączenie
            if (count%1000==0) System.out.println("Kolejne 1000 komunikacji wykonanych; łącznie:" + count);
        }

    }
}
