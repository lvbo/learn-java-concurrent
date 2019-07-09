package io.github.lvbo.learn.java.concurrent.deadlock.sort;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-09 08:19
 */
public class Account {

    private int id;
    private int balance;

    public void transform(Account target, int balance) {
        Account left = this;
        Account right = target;
        if (left.getId() > right.getId()) {
            left = target;
            right = this;
        }

        synchronized (left) {
            synchronized (right) {
                if (this.balance > balance) {
                    this.balance -= balance;
                    target.setBalance(target.getBalance() + balance);
                }
            }
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
