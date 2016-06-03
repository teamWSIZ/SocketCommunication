package Udp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static com.google.common.collect.ComparisonChain.start;

/**
 * Created by mareckip on 03.06.16.
 */
public class C_wielokrotny_pooling {
    public static void main(String[] args) throws Exception {
        long st = System.currentTimeMillis();
        //Uruchamiamy 10 wątków
        for (int i = 0; i < 10; i++) {
            Integer id = i;
            new Thread(() -> {
                System.out.println("Startuje wątek nr:" + id);
                //Każdy wątek nawiązuje połączenie TCP 1000 razy, i wysyła tekst, po czym zamyka połączenie
                try {
                    Socket s = new Socket("127.0.0.1", 22222);
                    OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
                    BufferedWriter bw = new BufferedWriter(osw);
                    for (int j = 0; j < 1000; j++) {
                        bw.write("Abra kadabra");
                        bw.newLine();
                        bw.write("ed");
                        bw.newLine();
                    }
                    bw.write("ex");
                    bw.flush();
                    s.close();
                } catch (Exception e) {
                    e.printStackTrace();

                }
                System.out.println("Wątek zakończony nr:" + id);
            }).start();
        }
        System.out.println("Koniec po " + (System.currentTimeMillis() - st) + "[ms]");

    }
}
