package com.mjy.connector.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: Mo Jianyue
 * @Description
 * @Date: 2023/1/3 8:21 PM
 * @Modified By
 */
public class ConnectorTest {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        try(Jedis resource = jedisPool.getResource()){
            resource.auth("123456");
            resource.set("jedi2s","aaa");
        }
        new jedispooled
    }
}
