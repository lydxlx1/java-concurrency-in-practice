package ch6;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by yuan on 10/14/16.
 */
public class OutOfTime {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(1);
        System.out.printf("I am here");
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(5);
        System.out.println("I am there");
    }

    static class ThrowTask extends TimerTask {
        public void run() {
            throw new RuntimeException();
        }
    }
}
