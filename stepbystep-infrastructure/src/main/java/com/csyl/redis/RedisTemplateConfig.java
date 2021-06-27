package com.csyl.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisTemplateConfig {

    @Bean
    @ConditionalOnMissingBean(name = "jsonRedisTemplate")
    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public JsonRedisTemplate jsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        JsonRedisTemplate template = new JsonRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


    public static class JsonRedisTemplate extends RedisTemplate<String, Object> {
        public JsonRedisTemplate() {
            setKeySerializer(RedisSerializer.string());
            setValueSerializer(jsonPair());
            setHashKeySerializer(RedisSerializer.string());
            setHashValueSerializer(jsonPair());
        }

        /**
         * 这种配置的风格于RedisSerializer.json()一样
         *
         * @return
         */
        private Jackson2JsonRedisSerializer<Object> jsonPair() {
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
            //解决查询缓存转换异常的问题
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
            jackson2JsonRedisSerializer.setObjectMapper(mapper);
            return jackson2JsonRedisSerializer;
        }

        public JsonRedisTemplate(RedisConnectionFactory connectionFactory) {
            this();
            setConnectionFactory(connectionFactory);
            afterPropertiesSet();
        }

        @Override
        protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
            return new DefaultStringRedisConnection(connection);
        }
    }

}
