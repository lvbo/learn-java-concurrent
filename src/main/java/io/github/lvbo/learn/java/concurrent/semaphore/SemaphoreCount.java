package io.github.lvbo.learn.java.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-11 08:11
 */
public class SemaphoreCount {

    private int count;
    private Semaphore semaphore = new Semaphore(1);

    public void addOne() throws InterruptedException {
        semaphore.acquire();
        try {
            ++ count;
        } finally {
            semaphore.release();
        }
    }

    public int getCount() {
        return count;
    }
}
