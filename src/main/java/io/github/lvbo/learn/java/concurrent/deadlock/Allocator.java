package io.github.lvbo.learn.java.concurrent.deadlock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-09 08:15
 */
public class Allocator {

    private static final Allocator instance = new Allocator();
    private List<Object> lockObjects = new ArrayList<Object>();

    private Allocator() {

    }

    public static Allocator getInstance() {
        return instance;
    }

    public synchronized boolean apply(Object from, Object to) {
        if (!lockObjects.contains(from) && !lockObjects.contains(to)) {
            lockObjects.add(from);
            lockObjects.add(to);
            return true;
        } else {
            return false;
        }
    }

    public synchronized void release(Object from, Object to) {
        lockObjects.remove(from);
        lockObjects.remove(to);
    }
}
