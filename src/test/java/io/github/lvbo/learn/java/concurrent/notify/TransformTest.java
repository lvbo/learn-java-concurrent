package io.github.lvbo.learn.java.concurrent.notify;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-09 09:11
 */
public class TransformTest {

    @Test
    public void testTransform() {
        Account from = new Account();
        from.setBalance(100);
        Account to = new Account();
        to.setBalance(100);

        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(new TransformThread(from, to, 10));
            t.start();
        }

        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(new TransformThread(to, from, 10));
            t.start();
        }

        try {
            Thread.sleep(5000);
            assertEquals(100, from.getBalance());
            assertEquals(100, to.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
