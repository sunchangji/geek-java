package com.suncj.cache.cache;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname RedisSubscriberComponent
 * @Description TODO
 * @Date 2021/9/3 4:37 下午
 * @Created by sunchangji
 */
@Component
public class RedisSubscriberComponent extends MessageListenerAdapter {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(message);
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String msg = redisTemplate.getStringSerializer().deserialize(body);
        String topic = redisTemplate.getStringSerializer().deserialize(channel);
         System.out.println("监听到topic为" + topic + "的消息：" + msg);
    }
}
