package com.csyl.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;

/**
 * @author 霖
 */
@Configuration
public class RedisAppConfig {

    @Bean(destroyMethod = "destroy")
    @ConditionalOnMissingBean
    public JedisConnectionFactory redisConnectionFactory() {
        //jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration
                .builder()
                .clientName("def_client")
                .connectTimeout(Duration.ofSeconds(5))
                .readTimeout(Duration.ofSeconds(10))
                .build();

        //redis所连接到的服务器配置
        RedisStandaloneConfiguration redisStandalone = new RedisStandaloneConfiguration();
        redisStandalone.setHostName("127.0.0.1");
        redisStandalone.setPort(6379);
        redisStandalone.setDatabase(1);

        return new JedisConnectionFactory(redisStandalone, jedisClientConfiguration);
    }

    public void destroy() {
    }
}
