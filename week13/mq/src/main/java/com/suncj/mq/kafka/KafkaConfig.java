package com.suncj.mq.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;

/**
 * 作业:1、搭建一个 3 节点 Kafka 集群，测试功能和性能；
 *     2、实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github
 * @Classname KafkaConfig
 * @Date 2021/9/15 11:20 上午
 * @Created by sunchangji
 */
@Configuration
public class KafkaConfig {

    /**
     * 新建一个异常处理器,当监听消费抛出异常的时候，则会自动调用异常处理器
     */
    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
        return (message, exception, consumer) -> {
            System.out.println("消费异常："+message.getPayload());
            return null;
        };
    }
}
