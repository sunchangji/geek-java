package com.suncj.cache.cache.controller;

import com.suncj.cache.cache.DistributedLockComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Classname RedisLockTestController
 * @Description TODO
 * @Date 2021/9/3 2:49 下午
 * @Created by sunchangji
 */
@RestController
@RequestMapping("test")
public class RedisLockTestController {

    @Autowired
    private DistributedLockComponent distributedLockComponent;

    @GetMapping("lock")
    public String lockTest(String key){
        String value = UUID.randomUUID().toString();
        if(distributedLockComponent.lock(key,value)){
            return "加锁成功,value="+value;
        }
        return "加锁失败";
    }

    @GetMapping("unlock")
    public String unlockTest(String key,String value){
        if(distributedLockComponent.unlockLua(key,value)){
            return "解锁锁成功";
        }
        return "解锁失败";
    }
}
