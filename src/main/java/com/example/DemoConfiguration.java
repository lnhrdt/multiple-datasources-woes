package com.example;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class DemoConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix="datasource.pets")
    public DataSource petsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="datasource.plants")
    public DataSource plantsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean petsEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(petsDataSource())
                .packages("com.example.pets")
                .persistenceUnit("pets")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean plantsEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(petsDataSource())
                .packages("com.example.plants")
                .persistenceUnit("plants")
                .build();
    }
}
