package zad13;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CountEdgesInGraphConcurrent {

    public static int countEdges(int n, boolean[][] graph, AtomicInteger counter) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(n - 2);
        for(int i = 0; i < n - 1; i++){
            new EdgeCounter(graph, i, n, counter, latch).run();
        }
        latch.await();
        return counter.get();
    }

    public static void main(String[] args){
        AtomicInteger counter = new AtomicInteger(0);

    }
}
