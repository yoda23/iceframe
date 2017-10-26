package com.edt.service.impl;

import com.edt.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "redisTemplateTransactional")
    private RedisTemplate<String, Object> redisTemplateTransactional;

    @Override
    public void opsValue_set(String key, Object value) {
        redisTemplateTransactional.opsForValue().set(key, value);
    }

    @Override
    public void opsValue_set(String key, Object value, long timeOut,
                             TimeUnit timeUnit) {
        redisTemplateTransactional.opsForValue().set(key, value, timeOut,
                timeUnit);

    }

    @Override
    public void opsValue_set(String key, Object value, long long1) {
        redisTemplateTransactional.opsForValue().set(key, value, long1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsValue_get(String key, Class<T> elementType) {
        return (T) redisTemplateTransactional.opsForValue().get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsValue_get(String key, long long1, long long2,
                              Class<T> elementType) {
        return (T) redisTemplateTransactional.opsForValue().get(key, long1,
                long2);
    }

    @Override
    public void opsValue_delete(String key) {
        redisTemplateTransactional.delete(key);
    }

    @Override
    public void opsValue_delete(Collection<String> collection) {
        redisTemplateTransactional.delete(collection);
    }

    @Override
    public Boolean opsValue_setIfAbsent(String key, Object object) {
        return redisTemplate.opsForValue().setIfAbsent(key, object);
    }

    @Override
    public Boolean opsValue_setIfAbsent(String key, Object object, Long timeOut,
                                        TimeUnit timeUnit) {
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, object);
        redisTemplate.expire(key, timeOut, timeUnit);
        return flag;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsValue_getAndSet(String key, Class<T> elementType,
                                    Object object) {
        return (T) redisTemplate.opsForValue().getAndSet(key, object);
    }

    @Override
    public Long opsValue_increment(String key, long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    @Override
    public Double opsValue_increment(String key, double value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    @Override
    public List<Object> opsValue_MultiGet(Collection<String> collection) {
        return redisTemplateTransactional.opsForValue().multiGet(collection);
    }

    @Override
    public Boolean opsValue_MultiSetIfAbsent(Map<String, Object> map) {
        return redisTemplateTransactional.opsForValue().multiSetIfAbsent(map);
    }

    @Override
    public void opsValue_MultiSet(Map<String, Object> map) {

        redisTemplateTransactional.opsForValue().multiSet(map);
    }

    // --------------------------------------------------------------------------------------------------//
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> opsList_range(String key, Class<T> elementType,
                                     long long1, long long2) {
        return (List<T>) redisTemplateTransactional.opsForList().range(key,
                long1, long2);
    }

    @Override
    public void opsList_trim(String key, long long1, long long2) {
        redisTemplateTransactional.opsForList().trim(key, long1, long2);
    }

    @Override
    public long opsList_size(String key) {
        return redisTemplateTransactional.opsForList().size(key);
    }

    @Override
    public Long opsList_leftPush(String key, Object object) {
        return redisTemplateTransactional.opsForList().leftPush(key, object);
    }

    @Override
    public Long opsList_leftPush(String key, Object object1, Object object2) {
        return redisTemplateTransactional.opsForList().leftPush(key, object1,
                object2);
    }

    @Override
    public Long opsList_leftPush(String key, Object object, Long timeOut,
                                 TimeUnit timeUnit) {
        redisTemplateTransactional.expire(key, timeOut, timeUnit);
        return opsList_leftPush(key, object);
    }

    @Override
    public Long opsList_leftPushAll(String key, Object... object) {
        return redisTemplateTransactional.opsForList().leftPushAll(key, object);
    }

    @Override
    public Long opsList_leftPushAll(String key, Collection<Object> collection) {
        return redisTemplateTransactional.opsForList().leftPushAll(key,
                collection);
    }

    @Override
    public Long opsList_leftPushIfPresent(String key, Object object) {
        return redisTemplate.opsForList().leftPushIfPresent(key, object);
    }

    @Override
    public Long opsList_rightPush(String key, Object object) {
        return redisTemplateTransactional.opsForList().rightPush(key, object);
    }

    @Override
    public Long opsList_rightPush(String key, Object object, Long timeOut,
                                  TimeUnit timeUnit) {
        redisTemplateTransactional.expire(key, timeOut, timeUnit);
        return opsList_rightPush(key, object);
    }

    @Override
    public Long opsList_rightPushAll(String key, Object... object) {
        return redisTemplateTransactional.opsForList().rightPushAll(key,
                object);
    }

    @Override
    public Long opsList_rightPushAll(String key,
                                     Collection<Object> collection) {
        return redisTemplateTransactional.opsForList().rightPushAll(key,
                collection);
    }

    @Override
    public Long opsList_rightPushIfPresent(String key, Object object) {
        return redisTemplateTransactional.opsForList().rightPushIfPresent(key,
                object);
    }

    @Override
    public void opsList_set(String key, long long1, Object object) {
        redisTemplateTransactional.opsForList().set(key, long1, object);
    }

    @Override
    public long opsList_remove(String key, long index, Object object) {
        return redisTemplate.opsForList().remove(key, index, object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsList_index(String key, long index, Class<T> elementType) {
        return (T) redisTemplateTransactional.opsForList().index(key, index);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsList_leftPop(String key, Class<T> elementType) {
        return (T) redisTemplateTransactional.opsForList().leftPop(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsList_leftPop(String key, Class<T> elementType, long long1,
                                 TimeUnit timeUnit) {
        return (T) redisTemplateTransactional.opsForList().leftPop(key, long1,
                timeUnit);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsList_rightPop(String key, Class<T> elementType) {
        return (T) redisTemplateTransactional.opsForList().rightPop(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsList_rightPop(String key, Class<T> elementType, long long1,
                                  TimeUnit timeUnit) {
        return (T) redisTemplateTransactional.opsForList().rightPop(key, long1,
                timeUnit);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsList_rightPopAndLeftPush(String from, String to,
                                             Class<T> elementType) {
        
        return (T) redisTemplate.opsForList().rightPopAndLeftPush(from, to);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T opsList_rightPopAndLeftPush(String from, String to,
                                             Class<T> elementType, long long1, TimeUnit timeUnit) {
        return (T) redisTemplate.opsForList().rightPopAndLeftPush(from, to,
                long1, timeUnit);
    }

    // --------------------------------------------------------------------------------------------------//
    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {

        return redisTemplateTransactional.getExpire(key, timeUnit);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplateTransactional.getExpire(key);
    }

    @Override
    public boolean expire(String key, Date date) {
        return redisTemplateTransactional.expireAt(key, date);
    }

    @Override
    public boolean expire(String key, Long timeOut, TimeUnit timeUnit) {
        return redisTemplateTransactional.expire(key, timeOut, timeUnit);
    }

}
