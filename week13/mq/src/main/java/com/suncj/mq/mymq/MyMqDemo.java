package com.suncj.mq.mymq;

import com.suncj.mq.mymq.broker.SuncjBroker;
import com.suncj.mq.mymq.broker.SuncjMessage;
import com.suncj.mq.mymq.comsumer.SuncjConsumer;
import com.suncj.mq.mymq.producer.SuncjProducer;
import lombok.SneakyThrows;

/**
 * @Classname MyMqDemo
 * @Description TODO
 * @Date 2021/9/16 4:52 下午
 * @Created by sunchangji
 */
public class MyMqDemo {
    @SneakyThrows
    public static void main(String[] args) {

        String topic = "suncj.test";
        SuncjBroker broker = new SuncjBroker();
        broker.createTopic(topic);

        SuncjConsumer consumer = broker.createConsumer();
        consumer.subscribe(topic);
        final boolean[] flag = new boolean[1];
        flag[0] = true;
        new Thread(() -> {
            while (flag[0]) {
                SuncjMessage message = consumer.poll();
                if(null != message) {
                    System.out.println("接收消息:"+message.getBody());
                }
            }
            System.out.println("程序退出。");
        }).start();

        SuncjProducer producer = broker.createProducer();
        for (int i = 0; i < 1000; i++) {
            Order order = new Order(1000L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
            producer.send(topic, new SuncjMessage(null, order));
        }
        Thread.sleep(500);
        System.out.println("点击任何键，发送一条消息；点击q或e，退出程序。");
        while (true) {
            char c = (char) System.in.read();
            if(c > 20) {
                System.out.println(c);
                producer.send(topic, new SuncjMessage(null, new Order(100000L + c, System.currentTimeMillis(), "USD2CNY", 6.52d)));
            }

            if( c == 'q' || c == 'e') break;
        }

        flag[0] = false;

    }
}
