package com.baizhi;

import com.baizhi.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Set;


@SpringBootTest
public class TestRedisTemplate {



    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate(){

        //修改key序列化方案
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Set keys = redisTemplate.keys("*");
        for (Object key : keys) {
            System.out.println(key);
        }

        redisTemplate.opsForValue().set("user", new User(null, null, null, null, null, null, null, 1));
        User user = (User) redisTemplate.opsForValue().get("user");//redis进行设置，对象需要经过序列化处理
        System.out.println(user);

        redisTemplate.opsForList().leftPush("list", new User());

    }


}
