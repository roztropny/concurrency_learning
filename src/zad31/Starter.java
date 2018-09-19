package zad31;

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
        AtomicBoolean foundFlag = new AtomicBoolean(false);
        String rightPassword = "cccc";
        String alfabet = "abc";
        int dlugoscSlowa = 4;
        new Producent(bufor, foundFlag, alfabet, dlugoscSlowa).start();
        new Consumer(bufor, foundFlag, rightPassword).start();

    }
}
