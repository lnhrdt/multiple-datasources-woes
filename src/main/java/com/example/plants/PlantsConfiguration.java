package com.example.plants;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.plants",
        entityManagerFactoryRef = "plantsEntityManagerFactory",
        transactionManagerRef = "plantsTransactionManager"
)
public class PlantsConfiguration {
}
