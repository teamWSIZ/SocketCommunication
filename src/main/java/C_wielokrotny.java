import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mareckip on 03.06.16.
 */
public class C_wielokrotny {
    public static void main(String[] args) throws Exception {
        long st = System.currentTimeMillis();
        AtomicLong end = new AtomicLong(st);
        //Uruchamiamy 10 wątków
        ExecutorService service = Executors.newFixedThreadPool(10);
        int LICZBA_POLACZEN = 1000;
        int LICZBA_ZAPYTAN_PER_POLACZENIE = 1000;
        for (int i = 0; i < LICZBA_POLACZEN; i++) {
            Integer id = i;
            service.execute(() -> {
                //Każdy wątek nawiązuje połączenie TCP 1000 razy, i wysyła tekst, po czym zamyka połączenie
                try {
                    Socket s = new Socket("127.0.0.1", 22222);
                    OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
                    BufferedWriter bw = new BufferedWriter(osw);
                    for (int j = 0; j < LICZBA_ZAPYTAN_PER_POLACZENIE; j++) {
                        bw.write("Abra kadabra");   //1000000 razy prosta komunikacja na "otwartym" połączeniu TCP
                        bw.newLine();
                        bw.write("eks");
                        bw.newLine();
                        bw.flush();
                    }
                    bw.write("ex"); //kończymy
                    bw.newLine();
                    bw.flush();
                    s.close();
                    System.out.println("Kończę zadanie id:" + id);
                    end.set(System.currentTimeMillis());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);  //tyle czekamy na zespół by dokończył pracę
        System.out.println("Czas wykonania całości: " + (end.get() - st) + "[ms]");
    }
}
