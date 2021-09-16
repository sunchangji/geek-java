package com.suncj.mq.mymq.broker;

import com.suncj.mq.mymq.comsumer.SuncjConsumer;
import com.suncj.mq.mymq.producer.SuncjProducer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname SunCjBroker
 * @Description TODO
 * @Date 2021/9/16 3:39 下午
 * @Created by sunchangji
 */
public class SuncjBroker {
    public static final int CAPACITY = 100000;

    private final Map<String, SuncjMq> mqMap = new ConcurrentHashMap<>(64);

    public void createTopic(String name){
        mqMap.putIfAbsent(name, new SuncjMq(name,CAPACITY));
    }

    public SuncjMq findKmq(String topic) {
        return this.mqMap.get(topic);
    }

    public SuncjProducer createProducer() {
        return new SuncjProducer(this);
    }

    public SuncjConsumer createConsumer() {
        return new SuncjConsumer(this);
    }
}
