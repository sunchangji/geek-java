package com.suncj.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Topic;

/**
 * @Classname ProducerService
 * @Description 消息生产
 * @Date 2021/9/8 11:04 上午
 * @Created by sunchangji
 */
@Service
public class ProducerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 使用指定消息队列发送
     * @param destination
     * @param message
     */
    public void sendMsg(Destination destination, String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    /**
     * 消息发布者
     * @param topic
     * @param msg
     */
    public void publish(Topic topic,String msg){
        jmsMessagingTemplate.convertAndSend(topic, msg);
    }
}
