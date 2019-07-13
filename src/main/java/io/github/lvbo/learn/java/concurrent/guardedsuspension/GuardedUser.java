package io.github.lvbo.learn.java.concurrent.guardedsuspension;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-13 11:12
 */
public class GuardedUser {
    private static AtomicLong al = new AtomicLong(0);

    /**
     * 处理浏览器发来的请求
      */
    private void handleWebReq() {
        long id = al.getAndIncrement();
        Message msg1 = new Message(id, "{...}");
        GuardedObject<Message> go = GuardedObject.create(id);
        send(msg1);
        Message r = go.get( t -> t != null);
    }

    private void send(Message msg1) {
    }

    void onMessage(Message msg) {
        // 唤醒等待的线程
        GuardedObject.fireEvent( msg.getId(), msg);
    }

}
