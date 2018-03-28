package zad11;

public class TimeToRunThreads {

    private static final int MAX_THREADS = 1000;
    private static final int HOW_MANY = 100;

    public static void main(String[] args) throws InterruptedException {
        long avg = 0;
        for(int i = 0; i < HOW_MANY; i++) {
            long startTime = System.nanoTime();
            for (int j = 0; j < MAX_THREADS; j++) {
                Thread thread = new Thread();
                thread.start();
                thread.join();
            }
            long elapsedTime = (System.nanoTime() - startTime
            ) / 1000000;
            avg += elapsedTime;
            //System.out.println(i + 1 + ". " + elapsedTime + " ms");
            System.out.println(elapsedTime);
        }
        avg /= HOW_MANY;
        System.out.println("Avg of " + HOW_MANY + " : " + avg);
    }
}
