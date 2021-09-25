package com.csyl.deserialization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * @author 霖
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }

    public static <T> String objToJson(T obj) {
        if (obj == null) {
            return null;
        }

        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("obj To json is error", e);
            return null;
        }
    }

    /**
     * 返回格式化好的json串
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objToJsonPretty(T obj) {
        if (obj == null) {
            return null;
        }

        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("obj To json pretty is error", e);
            return null;
        }
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) json : objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.warn("json To obj is error", e);
            return null;
        }
    }

    /**
     * 通过   TypeReference    处理List<User>这类多泛型问题
     *
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String json, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(json) || typeReference == null) {
            return null;
        }

        try {
            return (T) (typeReference.getType().equals(String.class) ? json : objectMapper.readValue(json, typeReference));
        } catch (Exception e) {
            log.warn("json To obj is error", e);
            return null;
        }
    }

    /**
     * 通过jackson 的javatype 来处理多泛型的转换
     *
     * @param json
     * @param collectionClazz
     * @param element
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String json, Class<? extends Collection> collectionClazz, Class<?> element) {
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(collectionClazz, element);

        try {
            return objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            log.warn("json To obj is error", e);
            return null;
        }
    }
}
