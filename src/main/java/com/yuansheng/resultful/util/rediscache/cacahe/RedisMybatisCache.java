package com.yuansheng.resultful.util.rediscache.cacahe;



import com.yuansheng.resultful.util.SerializeUtil;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
    mybatis的二级缓存，一级默认缓存
 */
public class RedisMybatisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(RedisMybatisCache.class);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;


    public RedisMybatisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.debug("MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public void clear() {
        redisTemplate.execute(new RedisCallback(){
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                redisConnection.flushAll();
                return null;
            }
        });

//        JedisConnection  connection = null;
//        try
//        {
//            connection = jedisConnectionFactory.getConnection();
//            connection.flushDb();
//            connection.flushAll();
//        }
//        catch (JedisConnectionException e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            if (connection != null) {
//                connection.close();
//            }
//        }
    }


    @Override
    public Object getObject(Object key) {
        try {
            if(key != null){
                Object obj = redisTemplate.opsForValue().get(key.toString());
                return SerializeUtil.deserialize((byte[])obj);
            }
        } catch (Exception e) {
            logger.error("getObject "+e);
        }
        return null;
    }



    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if(value != null){
            redisTemplate.opsForValue().set(key.toString(), SerializeUtil.serialize(value));
        }
    }



    @Override
    public Object removeObject(Object key) {
        try {
            if(key != null){
                redisTemplate.expire(key.toString(), 1, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            logger.error("removeObject "+e);
        }
        return null;
    }



    @Override
    public int getSize() {
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
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
