package io.github.lvbo.learn.java.concurrent.lock.reentrant;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-10 08:28
 */
public class ReentrantXTest {

    @Test
    public void addOne() throws InterruptedException {
        ReentrantX reentrantX = new ReentrantX();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Thread thread;
        for (int i = 0; i < 100; i++) {
            thread = new Thread(() -> {
                reentrantX.addOne();
                countDownLatch.countDown();
            });
            thread.start();
        }

        countDownLatch.await();
        assertEquals(100, reentrantX.getValue());
    }
}