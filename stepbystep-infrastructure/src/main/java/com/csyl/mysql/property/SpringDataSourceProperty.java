package com.csyl.mysql.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 霖
 */
@ConfigurationProperties(prefix = "spring.mysql")
@Getter
@Setter
public class SpringDataSourceProperty {

    private Map<String, DataSourceProperty> datasource = new LinkedHashMap<>();
    private String order;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataSourceProperty{

        /**
         * 连接池名称(只是一个名称标识)</br> 默认是配置文件上的名称
         */
        private String poolName;
        /**
         * 连接池类型，如果不设置自动查找 Druid > HikariCp
         */
        private Class<? extends DataSource> type;
        /**
         * JDBC driver
         */
        private String driverClassName;
        /**
         * JDBC url 地址
         */
        private String url;
        /**
         * JDBC 用户名
         */
        private String username;
        /**
         * JDBC 密码
         */
        private String password;
        /**
         * jndi数据源名称(设置即表示启用)
         */
        private String jndiName;
        /**
         * 自动运行的建表脚本
         */
        private String schema;
        /**
         * 自动运行的数据脚本
         */
        private String data;
    }
}
