package io.github.lvbo.learn.java.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-11 07:29
 */
public class BlockingQueue<T> {

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private int queueSize = 0;

    /**
     * 出队列
     * @return 出队列的元素
     * @throws InterruptedException
     */
    public T deq() throws InterruptedException {
        lock.lock();
        try {
            while (queueSize == 0) {
                notEmpty.await();
            }
            -- queueSize;
            System.out.println("----- deq -----, queueSize=" +queueSize);
            notFull.signal();
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void enq(T element) throws InterruptedException {
        lock.lock();
        try {
            while (queueSize == 10) {
                notFull.await();
            }
            ++ queueSize;
            System.out.println("----- enq -----, queueSize=" +queueSize);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}
