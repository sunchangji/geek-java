package com.suncj.cache.cache.controller;

import com.suncj.cache.cache.DistributedCounterComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname DistributedCounterController
 * @Description TODO
 * @Date 2021/9/3 3:05 下午
 * @Created by sunchangji
 */
@RestController
@RequestMapping("redis")
public class DistributedCounterController {
    @Resource
    private DistributedCounterComponent distributedCounterComponent;

    @GetMapping("decrease/stock")
    public String decreaseStock(){
        String key = "stock_num";
        Long result = distributedCounterComponent.increase(key,10000L);
        System.out.println("result:"+result);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (long i = 0; i < result; i++) {
            executorService.execute(() -> {
                Long value = distributedCounterComponent.decrease(key);
                System.out.println(Thread.currentThread().getName()+","+value);
            });
        }
        return "执行";
    }
}
