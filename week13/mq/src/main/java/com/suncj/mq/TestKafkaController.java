package com.suncj.mq;

import com.suncj.mq.kafka.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname TestKafkaController
 * @Description TODO
 * @Date 2021/9/15 11:41 上午
 * @Created by sunchangji
 */
@RestController
@RequestMapping("kafka")
public class TestKafkaController {

    @Resource
    private KafkaProducer kafkaProducer;

    @GetMapping("test/normal")
    public String testSendNormalMsg(String message){
        kafkaProducer.sendNormalMessage("test_one",message);
        return "发送完成";
    }

    @GetMapping("test/callback")
    public String testSendCallbackMsg(String message){
        kafkaProducer.sendMessageCallback(message);
        return "发送完成";
    }

    @GetMapping("test/exp")
    public String testSendExpMsg(String message){
        kafkaProducer.sendMessage(message);
        return "发送完成";
    }
}
