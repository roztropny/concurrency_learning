package zad32;

import zad31.Bufor;
import zad31.Consumer;
import zad31.Producent;

import java.util.concurrent.atomic.AtomicBoolean;

public class Starter {
    private static void haslogen(int n, int L, int level, char[] alfabet, char[] haslo)
    {
        if (level == n) { haslo[level]=0; System.out.println(haslo); }
        else {
            for (int i=0;i<L;i++) {
                haslo[level]=alfabet[i];
                haslogen(n,L,level+1,alfabet,haslo);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Bufor bufor = new Bufor(10);
        Bufor bufor2 = new Bufor(10);
        AtomicBoolean foundFlag = new AtomicBoolean(false);
        String rightPassword = "cccc";
        String alfabet = "abc";
        int dlugoscSlowa = 4;
        new Producent(bufor, foundFlag, alfabet, dlugoscSlowa).start();
        new ConsumerProducent(bufor, bufor2, foundFlag).start();
        new Consumer(bufor2, foundFlag, rightPassword).start();

    }
}
