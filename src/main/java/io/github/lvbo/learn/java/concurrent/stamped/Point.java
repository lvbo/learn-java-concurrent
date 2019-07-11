package io.github.lvbo.learn.java.concurrent.stamped;

import java.util.concurrent.locks.StampedLock;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-12 07:03
 */
public class Point {

    private int x;
    private int y;
    private StampedLock sl = new StampedLock();

    public double getDistance() {
        long stamp = sl.tryOptimisticRead();

        int curX = x;
        int curY = y;

        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(curX * curX + curY * curY);
    }
}
