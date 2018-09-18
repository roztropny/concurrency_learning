package zad25;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class SearchTable {

    private static final int ARRAY_SIZE = 1000;
    private static final int CHUNK_SIZE = 100;
    private static final int VALUE = 73;
    private static final int THREADS = ARRAY_SIZE / CHUNK_SIZE;
    private static final int MIN = 1;
    private static final int MAX = 1000;

    private static int[] generateTable(int[] array, int min, int max){
        for(int i = 0; i < array.length; i++){
            array[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
        }
        return array;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean foundFlag = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(THREADS);
        int[] array = new int[ARRAY_SIZE];
        array = generateTable(array, MIN, MAX);
        System.out.println("Wygenerowano");
        for(int i = 0; i < ARRAY_SIZE; i += CHUNK_SIZE){
            new Searcher(array, i, i + CHUNK_SIZE, VALUE, foundFlag, latch);
        }
        System.out.println("Wyszukiwanie rozpoczete");
        latch.await();
        System.out.println("Czy znaleziono: "+ foundFlag);
    }
}
