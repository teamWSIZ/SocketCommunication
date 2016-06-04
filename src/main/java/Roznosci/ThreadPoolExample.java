package Roznosci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pm on 6/4/16.
 */
public class ThreadPoolExample {
    public static void main(String[] args) throws Exception{
        AtomicInteger i = new AtomicInteger(0);
        ExecutorService service = Executors.newFixedThreadPool(10); //tworzymy zespół 10 wątków

        for (int j = 0; j < 2000; j++) {
            service.execute(() -> i.incrementAndGet()); //wrzucamy (asynchronicznie) zespołowi zadania do wykonania
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);  //tyle czekamy na zespół by dokończył pracę
        System.out.println(i);
    }
}
