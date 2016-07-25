package com.example.pets;

import com.example.SharedDatabaseConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.pets",
        entityManagerFactoryRef = "petsEntityManagerFactory",
        transactionManagerRef = "petsTransactionManager"
)
public class PetsDatabaseConfiguration extends SharedDatabaseConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix="databases.pets.datasource")
    @Qualifier("pets")
    public DataSource petsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("databases.pets.jpa")
    public JpaProperties petsJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean petsEntityManagerFactory(
            JpaProperties petsJpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(petsJpaProperties);
        return builder
                .dataSource(petsDataSource())
                .packages("com.example.pets")
                .build();
    }

    @Bean
    @Primary
    public JpaTransactionManager petsTransactionManager(EntityManagerFactory petsEntityManagerFactory) {
        return new JpaTransactionManager(petsEntityManagerFactory);
    }
}
