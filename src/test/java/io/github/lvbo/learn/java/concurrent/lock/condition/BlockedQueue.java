package io.github.lvbo.learn.java.concurrent.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-10 13:30
 */
public class BlockedQueue<T> {

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
//    private final List<T>

}
