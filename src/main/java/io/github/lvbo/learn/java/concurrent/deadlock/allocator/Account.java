package io.github.lvbo.learn.java.concurrent.deadlock.allocator;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-09 08:19
 */
public class Account {

    private int balance;

    public void transform(Account target, int balance) {
        while (!Allocator.getInstance().apply(this, target)) {
        }

        try {
            synchronized (this) {
                synchronized (target) {
                    if (this.balance > balance) {
                        this.balance -= balance;
                        target.setBalance(target.getBalance() + balance);
                    }
                }
            }
        } finally {
            Allocator.getInstance().release(this, target);
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
