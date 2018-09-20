package zad41;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {

    @FXML
    private TextField inputField;
    @FXML
    private Label totalCount, timeFromStart, timeToEnd;
    @FXML
    private ProgressBar progressBar;

    private AtomicBoolean stopFlag;
    private AtomicBoolean pauseFlag;
    private Processing processing;
    private Stats stats;


    public Controller() {
        this.stopFlag = new AtomicBoolean(false);
        this.pauseFlag = new AtomicBoolean(false);
    }

    @FXML
    private void startProccessing(){

        if(this.pauseFlag.get()){
            this.pauseFlag.set(false);
            notifyAll();
        }else {
            int n = Integer.parseInt(inputField.getText());
            this.processing = new Processing(stopFlag, pauseFlag, n);
            totalCount.setText(String.valueOf(processing.getTotalPerm()));
            this.stats = new Stats(stopFlag, pauseFlag, processing, this);
            processing.start();
            stats.start();
        }
    }

    @FXML
    private void stopProccessing(){
        this.stopFlag.set(true);
        this.pauseFlag.set(false);
        notifyAll();
    }

    @FXML
    private void pauseProccessing(){
        this.pauseFlag.set(true);
    }

    public void setTimeFromStart(String time){
        timeFromStart.setText(time);
    }

    public void setTimeToEnd(String time){
        timeToEnd.setText(time);
    }

    public void setProgressBar(float percentage){
        progressBar.setProgress(percentage);
    }

}
