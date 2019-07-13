package io.github.lvbo.learn.java.concurrent.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-13 10:45
 */
public class ThreadId {
    private static final AtomicLong nextId = new AtomicLong(0);
    private static final ThreadLocal<Long> tl = ThreadLocal.withInitial(() -> {
        return nextId.getAndIncrement();
    });

    long get() {
        return tl.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(3);
        ThreadId threadId = new ThreadId();
        es.execute(() -> {
            System.out.println("T1: " + threadId.get());
            System.out.println("T1: " + threadId.get());
        });
        es.execute(() -> {
            System.out.println("T2: " + threadId.get());
        });
        es.execute(() -> {
            System.out.println("T3: " + threadId.get());
        });
        es.shutdown();
    }
}
