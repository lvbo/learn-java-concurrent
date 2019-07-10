package io.github.lvbo.learn.java.concurrent.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-10 08:25
 */
public class ReentrantX {

    private Lock rl = new ReentrantLock();
    private int value;

    public int getValue() {
        rl.lock();
        try {
            return value;
        } finally {
            rl.unlock();
        }
    }

    public void addOne() {
        rl.lock();
        try {
            this.value = 1 + getValue();
        } finally {
            rl.unlock();
        }
    }
}
