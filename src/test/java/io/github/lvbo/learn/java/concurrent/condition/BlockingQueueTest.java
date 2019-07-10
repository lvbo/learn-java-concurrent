package io.github.lvbo.learn.java.concurrent.condition;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-11 07:45
 */
public class BlockingQueueTest {

    @Test
    public void testBlockingQueue() throws InterruptedException {
        CountDownLatch countDownLatch  = new CountDownLatch(2000);
        BlockingQueue<Object> blockingQueue = new BlockingQueue<Object>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    blockingQueue.enq(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
            new Thread(() -> {
                try {
                    blockingQueue.deq();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }

}