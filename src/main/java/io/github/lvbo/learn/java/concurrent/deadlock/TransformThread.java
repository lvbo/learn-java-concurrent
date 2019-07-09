package io.github.lvbo.learn.java.concurrent.deadlock;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-09 09:07
 */
public class TransformThread implements Runnable {

    private Account from;
    private Account to;
    private int balance;

    public TransformThread(Account from, Account to, int balance) {
        this.from = from;
        this.to = to;
        this.balance = balance;
    }

    public void run() {
        from.transform(to, balance);
    }
}
