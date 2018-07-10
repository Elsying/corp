package com.yuansheng.resultful.util.rediscache.cacahe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


/**
 *
 * @描述: 静态注入中间类
 */
public class RedisCacheTransfer {
    @Autowired
    public void setRedisTemplate(RedisTemplate  redisTemplate) {
        RedisMybatisCache.setaRedisTemplate(redisTemplate);

    }
}
