package com.suncj.activemq.producer;
import com.suncj.activemq.SuncjConstants;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname PtPActivemqDemo
 * @Description 点对点模型
 * @Date 2021/9/8 11:03 上午
 * @Created by sunchangji
 */
@Component
public class ActivemqProducerDemo {

    @Resource
    private ProducerService producerService;

    /**
     * 点对点发送消息模型
     */
    public void ptpMessage(String message) {
        producerService.sendMsg(new ActiveMQQueue(SuncjConstants.PTP_QUEUE), message);
    }

    /**
     * 订阅发送消息模型
     */
    public void topicMessage(String message) {
        producerService.publish(new ActiveMQTopic(SuncjConstants.TOPIC), message);
    }
}
