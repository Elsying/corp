package com.yuansheng.resultful.util.shiro.cache;

import com.yuansheng.resultful.util.rediscache.cacahe.RedisMybatisCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

import javax.annotation.Resource;

/**
 *  自定义缓存管理器
 */

public class CustomCacheManager implements CacheManager,Destroyable {
    @Resource(name = "redisShiroCache")
    private RedisShiroCache redisShiroCache;
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        //根据缓存名字获取一个Cache,这简单的写了....
        return redisShiroCache;
    }

    @Override
    public void destroy() throws Exception {

    }
}
