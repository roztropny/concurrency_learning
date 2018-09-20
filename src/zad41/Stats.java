package zad41;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Math.toIntExact;

public class Stats extends Thread {

    private AtomicBoolean stopFlag;
    private AtomicBoolean pauseFlag;
    private Processing processing;
    private Controller controller;
    private long start;

    public Stats(AtomicBoolean stopFlag, AtomicBoolean pauseFlag, Processing processing, Controller controller) {
        this.stopFlag = stopFlag;
        this.pauseFlag = pauseFlag;
        this.processing = processing;
        this.controller = controller;
    }

    private void updateStats(){
        float current = processing.getCountedPerm();
        float total = processing.getTotalPerm();
        float barValue = current * 100.0f / total;
        int timeDiff = toIntExact((System.currentTimeMillis() - start) / 1000);
        int timeEnd = Math.round(timeDiff * 100.0f / barValue);
        controller.setProgressBar(barValue / 100.0f);
        controller.setTimeFromStart(String.valueOf(timeDiff));
        controller.setTimeToEnd(String.valueOf(timeEnd));
    }

    public void run() {
        start = System.currentTimeMillis();
        while (!stopFlag.get()){
            if (pauseFlag.get()){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            updateStats();
        }
    }
}
