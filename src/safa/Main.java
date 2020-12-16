package safa;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<?> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                System.out.println("Thread is running...");

                int time = random.nextInt(1000) + 2000;

                if(time > 2500){
                    throw new IOException("Time is too long...");
                }

                Thread.sleep(time);

                System.out.println("Thread wakes up....");

                return time;
            }
        });

        executor.shutdown();

        try {
            System.out.println("Thread return this value: "+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Application finihed...");

    }
}
