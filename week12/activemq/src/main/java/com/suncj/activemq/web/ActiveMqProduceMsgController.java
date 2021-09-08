package com.suncj.activemq.web;

import com.suncj.activemq.producer.ActivemqProducerDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname ActiveMqProduceMsgController
 * @Description TODO
 * @Date 2021/9/8 11:32 上午
 * @Created by sunchangji
 */
@RestController
@RequestMapping("actmq")
public class ActiveMqProduceMsgController {
    @Resource
    private ActivemqProducerDemo activemqProducerDemo;

    @GetMapping("ptp")
    public String ptpSendMsg(String msg){
        activemqProducerDemo.ptpMessage(msg);
        return "ptp send message ok!!!";
    }

    @GetMapping("topic")
    public String topicSendMsg(String msg){
        activemqProducerDemo.topicMessage(msg);
        return "topic send message ok!!!";
    }
}
