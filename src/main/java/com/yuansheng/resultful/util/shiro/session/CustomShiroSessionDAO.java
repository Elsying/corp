package com.yuansheng.resultful.util.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class CustomShiroSessionDAO extends EnterpriseCacheSessionDAO {
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Session> redisTemplate;


    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisTemplate.boundValueOps("shiro_session_" + sessionId.toString()).set(session, 30, TimeUnit.MINUTES);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = redisTemplate.boundValueOps("shiro_session_" + sessionId.toString()).get();
        }

        return session;
    }


    protected void doUpdate(Session session) {
        super.doUpdate(session);
        redisTemplate.boundValueOps("shiro_session_" + session.getId()).set(session, 30, TimeUnit.MINUTES);
    }

    protected void doDelete(Session session) {
        redisTemplate.delete("shiro_session_" + session.getId().toString());
        super.doDelete(session);
    }
}
