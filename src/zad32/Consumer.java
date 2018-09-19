package zad32;

import zad31.Bufor;

import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer extends Thread {

    private Bufor storage;
    private String rightPassword;
    private AtomicBoolean foundFlag;

    public Consumer(Bufor storage, AtomicBoolean foundFlag, String rightPassword) {
        this.storage = storage;
        this.foundFlag = foundFlag;
        this.rightPassword = rightPassword;
    }

    public void run(){
        while(!foundFlag.get()){
            String haslo = this.storage.get();
            System.out.println("Consumer ID "+Thread.currentThread().getId()+" sprawdzam haslo: "+haslo);
            if(this.rightPassword.equals(haslo)){
                System.out.println("Consumer ID "+Thread.currentThread().getId()+" haslo: "+haslo+" prawidlowe");
                foundFlag.set(true);
                break;
            }else if(haslo == null){
                System.out.println("Consumer ID "+Thread.currentThread().getId()+" koniec hasel");
                break;
            }
        }
    }

}
