package com.yuansheng.resultful.util.shiro.cache;

import com.yuansheng.resultful.util.SerializeUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisShiroCache<K, V> implements Cache<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(RedisShiroCache.class);
    @Resource
    private RedisTemplate<Object,String>  redisTemplate;
    private String keyPrefix = "shiro_redis_session:";

    public String getKeyPrefix() {
        return keyPrefix;
    }
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    /**
     * 获得byte[]型的key
     *
     */
    private byte[] getByteKey(Object key){
        if (key instanceof String){
            String preKey = this.keyPrefix + key;
            return preKey.getBytes();
        }else{
            return SerializeUtil.serialize(key);
        }
    }


    @Override
    //@SuppressWarnings("unchecked")
    public V get(K key) throws CacheException {
        try {
            if(key != null){
                Object obj = redisTemplate.opsForValue().get(key.toString());
                logger.debug("获得缓存数据");
                return (V) SerializeUtil.deserialize((byte[])obj);
            }
        } catch (Exception e) {
            logger.error("getObject "+e);
        }
        return null;
    }


    /**
     * 将shiro的缓存保存到redis中
     */
    @Override
    public V put(K key, V value) throws CacheException {
        V previos = get(key);
        if(value != null){
            redisTemplate.opsForValue().set(key.toString(), SerializeUtil.serialize(value).toString());
        }
        logger.debug("存入缓存数据"+value);
        return previos;
    }

    @Override
    public V remove(K key) throws CacheException {
        V previos = get(key);
        try {
            if(key != null){
                redisTemplate.expire(key.toString(), 1, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            logger.error("removeObject "+e);
        }
        return previos;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        Long size = redisTemplate.execute(new RedisCallback<Long>(){
            @Override
            //使用execute执行connection.dbSize()
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.dbSize();
            }
        });
        return size.intValue();
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V>values() {
        return null;
    }
}
