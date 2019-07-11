package io.github.lvbo.learn.java.concurrent.cyclicbarrier;

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-12 07:22
 */
public class CyclicBarrierX {

    private Vector<String> queue1 = new Vector<String>();
    private Vector<String> queue2 = new Vector<String>();

    private final Executor executor = Executors.newFixedThreadPool(1);

    private final CyclicBarrier cb = new CyclicBarrier(2, () -> {
       executor.execute(() -> check());
    });

    private void check() {
        System.out.println(queue1.remove(0) + " -- " + queue2.remove(0));
    }

    public void checkAll() {
        Thread t1 = new Thread(() -> {
           while (true) {
               try {
                   Thread.sleep(30L);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               queue1.add("111");
               try {
                   cb.await();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (BrokenBarrierException e) {
                   e.printStackTrace();
               }
           }
        });

        t1.start();
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue2.add("222");
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        t2.start();
    }

    public static void main(String[] args) {
        CyclicBarrierX cyclicBarrierX = new CyclicBarrierX();
        cyclicBarrierX.checkAll();
    }
}
