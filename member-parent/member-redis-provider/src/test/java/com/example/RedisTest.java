package com.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @Date 2020/5/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private Logger logger= LoggerFactory.getLogger(RedisTest.class);
    @Test
    public void test()  {

        Jedis jedis = new Jedis("192.168.85.128", 6379);
        jedis.auth("123456");
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.close();

    }
    @Test
    public void testSet()  {

        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("apple","red");

    }
    @Test
    public void testExSet(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("banana","yelllo",5000, TimeUnit.SECONDS);
    }

}
