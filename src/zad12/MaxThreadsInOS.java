package zad12;

public class MaxThreadsInOS {
    public static void main(String[] args){
        int i = 1;
        while(true){
            new EndlessRunner().start();
            System.out.println(i++);
        }
    }
}
