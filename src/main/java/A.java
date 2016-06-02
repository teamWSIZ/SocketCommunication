import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mareckip on 16.04.16.
 */

@Data
class Osoba {
    String imie;
    String nazwisko;
    Integer ranking;
}

public class A {
    public static void main(String[] args) {
        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("Woda", 10);
        mapa.put("Cukier", 5);
        mapa.put("Bezoensan", 800);
        System.out.println(mapa.get("Cukier"));
        System.out.println(mapa.containsKey("Kofeina"));
        System.out.println(mapa.keySet());

        Osoba osoba = new Osoba();
        osoba.setImie("Paweł");
        osoba.setRanking(123);

        System.out.println(osoba);

        Map<Osoba, Integer> spis = new HashMap<>();
        spis.put(osoba, 111);
        System.out.println(spis.get(osoba));

    }
}
