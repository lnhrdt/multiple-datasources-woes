package com.example.plants;

import com.example.SharedDatabaseConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.plants",
        entityManagerFactoryRef = "plantsEntityManagerFactory",
        transactionManagerRef = "plantsTransactionManager"
)
public class PlantsDatabaseConfiguration extends SharedDatabaseConfiguration {
    @Bean
    @ConfigurationProperties(prefix="databases.plants.datasource")
    @Qualifier("plants")
    public DataSource plantsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("databases.plants.jpa")
    public JpaProperties plantsJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean plantsEntityManagerFactory(
            JpaProperties plantsJpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(plantsJpaProperties);
        return builder
                .dataSource(plantsDataSource())
                .packages("com.example.plants")
                .build();
    }

    @Bean
    public JpaTransactionManager plantsTransactionManager(EntityManagerFactory plantsEntityManagerFactory) {
        return new JpaTransactionManager(plantsEntityManagerFactory);
    }
}
