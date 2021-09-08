package com.suncj.activemq.consumer;

import com.suncj.activemq.SuncjConstants;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Classname ConsumerService
 * @Description TODO
 * @Date 2021/9/8 11:18 上午
 * @Created by sunchangji
 */
@Component
public class ConsumerDemo {


    /**
     * 监听指定消息队列PTP模型消费
     * 使用此方式需要配置spring.jms.pub-sub-domain=false
     * @param text
     */
    @JmsListener(destination = SuncjConstants.PTP_QUEUE)
    public void receiveQueue(String text) {
        System.out.println("[ ptp Consumer收到的报文 : " + text + " ]");
    }

    /**
     * 使用topic订阅发布模式需要配置spring.jms.pub-sub-domain=true
     * @param text
     */
    @JmsListener(destination = SuncjConstants.TOPIC)
    public void topicReceiver1(String text) {
        System.out.println("TopicConsumer : receiver1 : " + text);
    }

    @JmsListener(destination = SuncjConstants.TOPIC)
    public void topicReceiver2(String text) {
        System.out.println("TopicConsumer : receiver2 : " + text);
    }

    @JmsListener(destination = SuncjConstants.TOPIC)
    public void topicReceiver3(String text) {
        System.out.println("TopicConsumer : receiver3 : " + text);
    }

}
