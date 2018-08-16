package com.pravin.spring.secuity.config;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:db.properties")
public class LoginAppDataConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
	dataSource.setUrl(env.getProperty("ds.url"));
	dataSource.setUsername(env.getProperty("ds.username"));
	dataSource.setPassword(env.getProperty("ds.password"));
	return dataSource;
    }
}
