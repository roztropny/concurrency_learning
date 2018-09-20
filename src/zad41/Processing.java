package zad41;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Processing extends Thread {

    private AtomicBoolean stopFlag;
    private AtomicBoolean pauseFlag;
    private AtomicInteger totalPerm;
    private AtomicInteger countedPerm;
    private int n;

    public Processing(AtomicBoolean stopFlag, AtomicBoolean pauseFlag, int n) {
        this.stopFlag = stopFlag;
        this.pauseFlag = pauseFlag;
        this.totalPerm = new AtomicInteger(this.totalPerm(n));
        this.countedPerm = new AtomicInteger(0);
        this.n = n;
    }

    public int totalPerm(int n){
        if(n == 0){
            return 0;
        }
        int total = 1;
        for(int i = 1; i <= n; i++){
            total = total * i;
        }
        return total;
    }

    public int getTotalPerm() {
        return totalPerm.get();
    }

    public int getCountedPerm() {
        return countedPerm.get();
    }

    public void permutation(String str) {
        permutation("", str);
    }

    private void permutation(String prefix, String str) {
        if (!stopFlag.get()) {
            if (pauseFlag.get()){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int n = str.length();
            if (n == 0) this.countedPerm.incrementAndGet();
            else {
                for (int i = 0; i < n; i++)
                    permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }

    private String nToStringSet(int n){
        String str = "";
        for(int i = 1; i <= n; i++){
            str = str + String.valueOf(i);
        }
        return str;
    }

    public void run(){
        permutation(nToStringSet(this.n));
    }

}
