package io.github.lvbo.learn.java.concurrent.guardedsuspension;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-13 11:15
 */
public class Message {
    private long id;
    private String msg;

    public Message(long id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
