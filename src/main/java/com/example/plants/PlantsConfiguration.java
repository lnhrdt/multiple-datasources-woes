package com.example.plants;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = Fern.class, entityManagerFactoryRef = "plantsEntityManagerFactory")
public class PlantsConfiguration {
}
