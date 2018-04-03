package zad25;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Searcher extends Thread {

    private int[] array;
    private int start;
    private int end;
    private int value;
    private AtomicBoolean foundFlag;
    private CountDownLatch latch;

    public Searcher(int[] array, int start, int end, int value, AtomicBoolean foundFlag, CountDownLatch latch) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.value = value;
        this.foundFlag = foundFlag;
        this.latch = latch;
    }

    public void run() {
        for(int i = start; i < end; i++){
            if(foundFlag.get()){
                latch.countDown();
                return;
            }
            if(array[i] == value){
                foundFlag.set(true);
                latch.countDown();
                return;
            }
        }
        latch.countDown();
    }
}
