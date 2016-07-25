package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class DemoConfiguration {

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    @Bean
    @Primary
    @ConfigurationProperties(prefix="databases.pets.datasource")
    @Qualifier("pets")
    public DataSource petsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="databases.plants.datasource")
    @Qualifier("plants")
    public DataSource plantsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("databases.pets.jpa")
    public JpaProperties petsJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    @ConfigurationProperties("databases.plants.jpa")
    public JpaProperties plantsJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean petsEntityManagerFactory(
            JpaProperties petsJpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(petsJpaProperties);
        return builder
                .dataSource(petsDataSource())
                .packages("com.example.pets")
                .persistenceUnit("pets")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean plantsEntityManagerFactory(
            JpaProperties plantsJpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(plantsJpaProperties);
        return builder
                .dataSource(plantsDataSource())
                .packages("com.example.plants")
                .persistenceUnit("plants")
                .build();
    }

    @Bean
    @Primary
    public JpaTransactionManager petsTransactionManager(EntityManagerFactory petsEntityManagerFactory) {
        return new JpaTransactionManager(petsEntityManagerFactory);
    }

    @Bean
    public JpaTransactionManager plantsTransactionManager(EntityManagerFactory plantsEntityManagerFactory) {
        return new JpaTransactionManager(plantsEntityManagerFactory);
    }

    private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties jpaProperties) {
        JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(jpaProperties);
        return new EntityManagerFactoryBuilder(jpaVendorAdapter, jpaProperties.getProperties(), persistenceUnitManager);
    }

    private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(jpaProperties.isShowSql());
        adapter.setDatabase(jpaProperties.getDatabase());
        adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        return adapter;
    }
}
