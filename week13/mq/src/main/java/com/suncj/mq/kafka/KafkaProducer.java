package com.suncj.mq.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname KafkaProducer
 * @Description TODO
 * @Date 2021/9/15 11:32 上午
 * @Created by sunchangji
 */
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送消息
     *
     * @param normalMessage
     */
    public void sendNormalMessage(String topic, String normalMessage) {
        //ListenableFuture<SendResult<String, Object>> result =
        kafkaTemplate.send(topic, normalMessage);

    }

    /**
     * 回调方法addCallback，我们可以在回调方法中监控消息是否发送成功 或 失败时做补偿处理
     *
     * @param callbackMessage
     */
    public void sendMessageCallback(String callbackMessage) {
        kafkaTemplate.send("test_one", callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }


    /**
     * 回调方法addCallback，我们可以在回调方法中监控消息是否发送成功 或 失败时做补偿处理
     *
     * @param callbackMessage
     */
    public void sendMessage(String callbackMessage) {
        kafkaTemplate.send("test_exp", callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }
}
