package zad13;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class EdgeCounter implements Runnable{

    private boolean[][] graph;
    private int i;
    private int n;
    AtomicInteger counter;
    CountDownLatch latch;

    public EdgeCounter(boolean[][] graph, int i, int n, AtomicInteger counter, CountDownLatch latch) {
        this.graph = graph;
        this.i = i;
        this.n = n;
        this.counter = counter;
        this.latch = latch;
    }

    public void run() {
        for(int j = i + 1; j < n; j++)
            if(graph[i][j])
                counter.incrementAndGet();
        latch.countDown();
    }
}
