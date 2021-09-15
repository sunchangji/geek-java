package com.suncj.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname KafkaConsumer
 * @Description TODO
 * @Date 2021/9/15 11:11 上午
 * @Created by sunchangji
 */
@Component
public class KafkaConsumer {

    /**
     * 消费监听
     *
     * @param message
     */
    @KafkaListener(topics = {"test_one"})
    public void onMessage(ConsumerRecord<?, ?> message) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费：topic=" + message.topic() + ";partition=" + message.partition() + ";value=" + message.value());
    }

    /**
     * 消费监听
     *
     * @param message
     */
    @KafkaListener(topics = {"test_exp"}, errorHandler = "consumerAwareErrorHandler")
    public void onMessageException(ConsumerRecord<?, ?> message) throws Exception {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费：topic=" + message.topic() + ";partition=" + message.partition() + ";value=" + message.value());
        throw new Exception("消费失败");
    }

    /**
     * 批量消费
     * 需要打开配置文件中
     * spring.kafka.listener.type=batch
     * spring.kafka.consumer.max-poll-records=50
     * @param messages
     * @throws Exception
     */
    @KafkaListener(topics = "test_batch")
    public void batchMessages(List<ConsumerRecord<?, ?>> messages) {
        System.out.println("批量消费一次...");
        messages.forEach(msg -> {
            System.out.println("批量消费,topic=" + msg.topic() + ";partition=" + msg.partition() + ";" + msg.value());
        });
    }

    /**
     * 批量消费异常
     *
     * @param messages
     * @throws Exception
     */
    @KafkaListener(topics = "test_batch_exp", errorHandler = "consumerAwareErrorHandler")
    public void batchMessagesException(List<ConsumerRecord<?, ?>> messages) throws Exception {
        System.out.println("批量消费一次...");
        messages.forEach(msg -> {
            System.out.println("批量消费,topic=" + msg.topic() + ";partition=" + msg.partition() + ";" + msg.value());
        });

        throw new Exception("批量消费-模拟异常");
    }
}
