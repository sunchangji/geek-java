package com.suncj.cache.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname DistributedCounterComponent
 * @Description 分布式计数器
 * @Date 2021/9/3 2:57 下午
 * @Created by sunchangji
 */
@Component
public class DistributedCounterComponent {
    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    /**
     * 每次加一
     * @param key
     * @return
     */
    public Long increase(String key){
        return redisTemplate.opsForValue().increment(key);
    }

    public Long decrease(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }


    /**
     * 每次加指定数值
     * @param key
     * @param delta
     * @return
     */
    public Long increase(String key,Long delta){
        return redisTemplate.opsForValue().increment(key,delta);
    }

    public Long decrease(String key,Long delta){
        if(null == delta){
            return redisTemplate.opsForValue().decrement(key);
        }
        return redisTemplate.opsForValue().decrement(key,delta);
    }
}
