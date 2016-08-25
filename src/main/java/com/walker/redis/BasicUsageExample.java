package com.walker.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * Created by walker on 2016/8/26.
 */
public class BasicUsageExample {
    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");

        //1.try with resources
        /// Jedis implements Closable. Hence, the jedis instance will be auto-closed after the last statement.
        try (Jedis jedis = pool.getResource()) {
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        }

        //2.If you can't use try-with-resource, see below:
//        Jedis jedis = null;
//        try {
//            jedis = pool.getResource();
//            /// ... do stuff here ... for example
//            jedis.set("foo", "bar");
//            String foobar = jedis.get("foo");
//            jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike");
//            Set<String> sose = jedis.zrange("sose", 0, -1);
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }

        /// 3.... when closing your application；应用关闭时候销毁池。
        pool.destroy();






    }
}
