package my.com.chat;

/**
 * Created by 鲍建明 on 2015/3/16.
 */
public class ChatMsg {

    public final static int SEND = 1;          //发送类型
    public final static int RECEIVED = 2;      //接收类型

    private int type;
    private String msg;

    public void setType(int type) {
        this.type = type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
