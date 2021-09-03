package com.suncj.cache.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Classname DistributedLockComponent
 * @Description TODO
 * @Date 2021/9/3 2:19 下午
 * @Created by sunchangji
 */
@Component
public class DistributedLockComponent {
    //lua脚本解锁
    private static final String UNLOCK_LUA = "if redis.call('get',KEYS[1]) == ARGV[1] then\n" +
            "    return redis.call('del',KEYS[1])\n" +
            "else\n" +
            "    return 0\n" +
            "end";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 加锁
     * @param key
     * @param value
     * @return
     */
    public Boolean lock(String key,String value){
        return redisTemplate.opsForValue().setIfAbsent(key,value,5, TimeUnit.MINUTES);
    }

    /**
     * 解锁,为了保证原子性
     * @param key
     * @param value
     * @return
     */
    public boolean unlockLua(String key,String value){
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(UNLOCK_LUA);
        redisScript.setResultType(Long.class);
        //没有指定序列化方式，默认使用上面配置的
        Object result = redisTemplate.execute(redisScript, Collections.singletonList(key), value);
        return Objects.equals(result,1L);
    }

}
