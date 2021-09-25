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

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        //下面的配置会把类型打印出来
        //OBJECT_MAPPER.activateDefaultTyping(OBJECT_MAPPER.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        OBJECT_MAPPER.activateDefaultTyping(OBJECT_MAPPER.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.PROPERTY);
    }

    public static <T> String objToJson(T obj) {
        if (obj == null) {
            return null;
        }

        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writeValueAsString(obj);
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
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
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
            return clazz.equals(String.class) ? (T) json : OBJECT_MAPPER.readValue(json, clazz);
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
            return (T) (typeReference.getType().equals(String.class) ? json : OBJECT_MAPPER.readValue(json, typeReference));
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
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(collectionClazz, element);

        try {
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.warn("json To obj is error", e);
            return null;
        }
    }
}
