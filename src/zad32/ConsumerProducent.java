package zad32;

import zad31.Bufor;

import java.util.concurrent.atomic.AtomicBoolean;

public class ConsumerProducent extends Thread {

    private Bufor storage;
    private Bufor storage2;
    private AtomicBoolean foundFlag;

    public ConsumerProducent(Bufor storage, Bufor storage2, AtomicBoolean foundFlag) {
        this.storage = storage;
        this.storage2 = storage2;
        this.foundFlag = foundFlag;
    }

    private String reverse(String str){
        String reverse = "";
        for(int i = str.length() - 1; i >= 0; i--){
            reverse = reverse + str.charAt(i);
        }
        return reverse;
    }

    public void run(){
        while(!foundFlag.get()){
            String haslo = this.storage.get();
            System.out.println("ConsumerProducer ID "+Thread.currentThread().getId()+" odwracam haslo: "+haslo);
            if(haslo == null){
                storage2.put(null);
                break;
            }else{
                haslo = reverse(haslo);
                storage2.put(haslo);
            }
        }
    }

}
