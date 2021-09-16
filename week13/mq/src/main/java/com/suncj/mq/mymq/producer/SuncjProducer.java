package com.suncj.mq.mymq.producer;

import com.suncj.mq.mymq.broker.SuncjBroker;
import com.suncj.mq.mymq.broker.SuncjMessage;
import com.suncj.mq.mymq.broker.SuncjMq;

/**
 * @Classname KmqProducer
 * @Description TODO
 * @Date 2021/9/16 3:40 下午
 * @Created by sunchangji
 */
public class SuncjProducer {

    private SuncjBroker broker;

    public SuncjProducer(SuncjBroker broker) {
        this.broker = broker;
    }

    public boolean send(String topic, SuncjMessage message) throws Exception {
        SuncjMq kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        return kmq.send(message);
    }
}
