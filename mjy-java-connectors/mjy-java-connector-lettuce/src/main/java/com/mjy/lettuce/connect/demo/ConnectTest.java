package com.mjy.lettuce.connect.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;

/**
 * @Author: Mo Jianyue
 * @Description
 * @Date: 2023/1/3 6:31 PM
 * @Modified By
 */
public class ConnectTest {
    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.builder().withHost("127.0.0.1")
                .withPort(6379)
                .withPassword("123456".toCharArray())
                .withTimeout(Duration.ofMinutes(5))
                .withDatabase(1)
                .build();
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> commands = connect.sync();
        System.out.println(commands.ping());
//        System.out.println(commands.flushdb());
        System.out.println("判断某个键是否存在："+commands.exists("username"));
        System.out.println("新增<'username','xmr'>的键值对："+commands.set("username", "xmr"));
        System.out.println("新增<'password','password'>的键值对："+commands.set("password", "123"));
        System.out.println("获取<'password'>键的值："+commands.get("password"));
        System.out.println("系统中所有的键如下：" + commands.keys("*"));
        System.out.println("删除键password:"+commands.del("password"));
        System.out.println("判断键password是否存在："+commands.exists("password"));
        System.out.println("设置键username的过期时间为5s:"+commands.expire("username", 5L));
        System.out.println("查看键username的剩余生存时间："+commands.ttl("username"));
        System.out.println("移除键username的生存时间："+commands.persist("username"));
        System.out.println("查看键username的剩余生存时间："+commands.ttl("username"));
        System.out.println("查看键username所存储的值的类型："+commands.type("username"));
        connect.close();
        redisClient.shutdown();
    }
}
