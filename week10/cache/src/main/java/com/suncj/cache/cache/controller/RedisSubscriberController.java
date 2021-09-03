package com.suncj.cache.cache.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname RedisSubscriberController
 * @Description TODO
 * @Date 2021/9/3 4:40 下午
 * @Created by sunchangji
 */
@RestController
public class RedisSubscriberController {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 下单
     * @return
     */
    @GetMapping("order")
    public String orderTake(String data){
        //下单信息部分信息设置到redis中
        redisTemplate.convertAndSend("test_topic", data);
        return "success";
    }
}
