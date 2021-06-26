package com.csyl.mysql.mybatis;

import com.csyl.mysql.property.SpringDataSourceProperty;
import lombok.AllArgsConstructor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author 霖
 */
@Primary
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({SpringDataSourceProperty.class})
public class MultipleDataSourceConfig {

    private final SpringDataSourceProperty springDataSourceProperty;

    /**
     * 创建多个数据源 ds1 和 ds2
     * 此处的Primary，是设置一个Bean的优先级
     *
     * @return
     */
    @Bean(name = "db1")
    @Primary
    public DataSource getDateSource1() {
        return getDataSource(springDataSourceProperty.getDatasource().get("db1"));
    }

    private DataSource getDataSource(SpringDataSourceProperty.DataSourceProperty dataSourceProperty) {
        return DataSourceBuilder.create()
                .type(com.zaxxer.hikari.HikariDataSource.class)
                .url(dataSourceProperty.getUrl())
                .password(dataSourceProperty.getPassword())
                .driverClassName(dataSourceProperty.getDriverClassName())
                .username(dataSourceProperty.getUsername())
                .build();
    }


    /**
     * 将动态数据源注入到SqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "SqlSessionFactory")
    @Primary
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("db1") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:mapper/**/*Mapper.xml"));

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(StdOutImpl.class);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCallSettersOnNulls(true);
        bean.setConfiguration(configuration);

        return bean.getObject();
    }

}
