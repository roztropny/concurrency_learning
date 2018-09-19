package zad31;

import java.util.concurrent.atomic.AtomicBoolean;

public class Producent extends Thread {
    private Bufor storage;
    private AtomicBoolean foundFlag;
    private String alfabet;
    private int dlugoscSlowa;

    public Producent(Bufor storage, AtomicBoolean foundFlag, String alfabet, int dlugoscSlowa) {
        this.storage = storage;
        this.foundFlag = foundFlag;
        this.alfabet = alfabet;
        this.dlugoscSlowa = dlugoscSlowa;
    }

    private void haslogen(int n, int L, int level, char[] alfabet, char[] haslo)
    {
        if(!foundFlag.get()) {
            if (level == n) {
                //haslo[level] = 0;
                storage.put(new String(haslo));
                System.out.println("Producent ID "+Thread.currentThread().getId()+" wlozono haslo: "+new String(haslo));
            } else {
                for (int i = 0; i < L; i++) {
                    haslo[level] = alfabet[i];
                    haslogen(n, L, level + 1, alfabet, haslo);
                }
            }
        }
    }

    public void run(){
        char[] alfabet = this.alfabet.toCharArray();
        char[] haslo = new char[this.dlugoscSlowa];
        this.haslogen(this.dlugoscSlowa, alfabet.length, 0, alfabet, haslo);
        if(!foundFlag.get()){
            storage.put(null);
            System.out.println("Producent ID "+Thread.currentThread().getId()+" hasla wyczerpane");
        }
    }
}
