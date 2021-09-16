package com.suncj.mq.mymq.broker;

/**
 * @Classname
 * @Description TODO
 * @Date 2021/9/16 11:48 上午
 * @Created by sunchangji
 */
public class SuncjMq {
    /**
     * topic
     */
    private String topic;
    /**
     * 队列长度
     */
    private int capacity;

    private SuncjMessage[] queue;

    /**
     * 消息写入位置
     */
    private int writeOffset;

    /**
     * 消息消费位置
     */
    private int consumerOffset;

    public SuncjMq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new SuncjMessage[capacity];
        this.writeOffset = -1;
        this.consumerOffset = -1;
    }

    /**
     * 生产消息
     * @param message
     * @return
     * @throws Exception
     */
    public synchronized boolean send(SuncjMessage message) throws Exception {
        if (writeOffset >= capacity) {
            throw new Exception("消息队列已满,写入失败");
        }
        writeOffset++;
        queue[writeOffset] = message;
        System.out.println("send writeOffset:"+writeOffset);
        return true;
    }

    /**
     * 消费消息
     * @return
     */
    public synchronized SuncjMessage poll() {
        SuncjMessage message = queue[consumerOffset+1];
        if(null != message){
            consumerOffset++;
            System.out.println("poll consumerOffset:"+consumerOffset);
        }
        return message;
    }
}
