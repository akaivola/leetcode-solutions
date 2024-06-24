package leetcode.h2o_1117;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

// 1117. Building H20
public class H2O {
    Semaphore hydrogen;
    Semaphore oxygen;

    final ReentrantLock lock;
    private int hydrogenCount = 0;

    public H2O() {
        hydrogen = new Semaphore(2);
        oxygen = new Semaphore(0);
        lock = new ReentrantLock();
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
//        System.out.println("hydrogen acquire");
        hydrogen.acquire();
        lock.lock();
        try {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hydrogenCount++;
            if (hydrogenCount == 2) {
                oxygen.release();
                hydrogenCount = 0;
            }
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire();

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();

        hydrogen.release(2);
    }

    public static void main(String... args) {
        BlockingQueue<String> water = new ArrayBlockingQueue<>(24);
        H2O h2o = new H2O();

        Stream<Runnable> h = Stream.generate(() -> () -> {
            System.out.println("H");
            boolean offered = water.offer("H");
        });
        Stream<Runnable> o = Stream.generate(() -> () -> {
            System.out.println("O");
            boolean offered = water.offer("O");
        });

        try (ExecutorService executorService = Executors.newFixedThreadPool(50, Thread.ofVirtual().factory())) {
            var hydrogenStream = h.limit(40).map(releaseHydrogen -> {
                try {
                    return CompletableFuture.runAsync(() -> {
                        try {
                            h2o.hydrogen(releaseHydrogen);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }, executorService).orTimeout(100, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            var oxygenStream = o.limit(20).map(releaseOxygen -> {
                try {
                    return CompletableFuture.runAsync(() -> {
                        try {
                            h2o.oxygen(releaseOxygen);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }, executorService).orTimeout(100, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });


            CompletableFuture.allOf(hydrogenStream.toArray(CompletableFuture[]::new));
            CompletableFuture.allOf(oxygenStream.toArray(CompletableFuture[]::new));
        }


        Optional<String> output = water.stream().reduce(String::concat);
        System.out.println(output.get());
    }
}