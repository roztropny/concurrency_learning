package zad32;

import java.util.concurrent.atomic.AtomicInteger;

public class Bufor {
    private String[] storage;
    private AtomicInteger counter;
    private int size;

    public Bufor(int size) {
        this.size = size;
        this.storage = new String[this.size];
        this.counter = new AtomicInteger(0);
    }

    public synchronized String get() {
        while (this.counter.get() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        String temp = storage[this.counter.decrementAndGet()];
        notifyAll();
        return temp;
    }
    public synchronized void put(String value) {
        while (this.counter.get() == this.size - 1) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        int counter = this.counter.get();
        this.counter.incrementAndGet();
        storage[counter] = value;
        notifyAll();
    }
}
