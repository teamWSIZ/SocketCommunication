import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by mareckip on 03.06.16.
 */
public class C_wielokrotny {
    public static void main(String[] args) throws Exception {
        //Uruchamiamy 10 wątków
        for (int i = 0; i < 10; i++) {
            Integer id = i;
            new Thread(() -> {
                System.out.println("Startuje wątek nr:" + id);
                //Każdy wątek nawiązuje połączenie TCP 1000 razy, i wysyła tekst, po czym zamyka połączenie
                for (int j = 0; j < 1000; j++) {
                    try {
                        Socket s = new Socket("127.0.0.1",22222);
                        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
                        BufferedWriter bw = new BufferedWriter(osw);
                        bw.write("Abra kadabra");
                        bw.newLine();
                        bw.write("ex");
                        bw.newLine();
                        bw.flush();
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println("Wątek zakończony nr:" + id);
            }).start();
        }

    }
}
