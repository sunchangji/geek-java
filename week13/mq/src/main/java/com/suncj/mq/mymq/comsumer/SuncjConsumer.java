package com.suncj.mq.mymq.comsumer;

import com.suncj.mq.mymq.broker.SuncjBroker;
import com.suncj.mq.mymq.broker.SuncjMessage;
import com.suncj.mq.mymq.broker.SuncjMq;

/**
 * @Classname SuncjConsumer
 * @Description TODO
 * @Date 2021/9/16 3:42 下午
 * @Created by sunchangji
 */
public class SuncjConsumer{
    private final SuncjBroker broker;

    private SuncjMq suncjMq;

    public SuncjConsumer(SuncjBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.suncjMq = this.broker.findKmq(topic);
        if (null == suncjMq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public SuncjMessage poll(){
        return this.suncjMq.poll();
    }
}
