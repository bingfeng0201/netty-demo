package com.dz.netty.live;

/**
 *
 * Created by RoyDeng on 17/11/23.
 */
public class LiveMessage {

    //心跳
    static final byte TYPE_HEART = 1;

    //消息
    static final byte TYPE_MESSAGE = 2;

    //消息类型-心跳和内容
    private byte type;
    //消息长度
    private int length;
    //消息内容-心跳包没有内容
    private String content;

    public LiveMessage() {}

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LiveMessage{" +
                "type=" + type +
                ", length=" + length +
                '}';
    }
}
