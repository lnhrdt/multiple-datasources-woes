package com.example.pets;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.pets",
        entityManagerFactoryRef = "petsEntityManagerFactory",
        transactionManagerRef = "petsTransactionManager"
)
public class PetsDatabaseConfiguration {
}
