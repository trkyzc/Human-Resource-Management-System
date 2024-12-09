package com.example.hrms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

//@Component
//public class RedisConnectionTest implements CommandLineRunner {
//
//    private final RedisConnectionFactory redisConnectionFactory;
//
//    public RedisConnectionTest(RedisConnectionFactory redisConnectionFactory) {
//        this.redisConnectionFactory = redisConnectionFactory;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            redisConnectionFactory.getConnection().ping();
//            System.out.println("Redis bağlantısı başarılı!");
//        } catch (Exception e) {
//            System.err.println("Redis bağlantısı başarısız: " + e.getMessage());
//        }
//    }
//}



@Component
public class RedisConnectionTest implements CommandLineRunner {

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisConnectionTest(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Override
    public void run(String... args) {
        try {
            String pingResponse = redisConnectionFactory.getConnection().ping();
            System.out.println("Redis bağlantısı başarılı! Yanıt: " + pingResponse);
        } catch (Exception e) {
            System.err.println("Redis bağlantısı başarısız: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
