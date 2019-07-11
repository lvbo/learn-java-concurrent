package io.github.lvbo.learn.java.concurrent.semaphore;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-11 08:13
 */
public class SemaphoreCountTest {

    @Test
    public void testCount() throws InterruptedException {
        SemaphoreCount semaphoreCount = new SemaphoreCount();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    semaphoreCount.addOne();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
        assertEquals(10000, semaphoreCount.getCount());
    }

}