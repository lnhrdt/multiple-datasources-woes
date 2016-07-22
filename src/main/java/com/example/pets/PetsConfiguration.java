package com.example.pets;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = Dog.class, entityManagerFactoryRef = "petsEntityManagerFactory")
public class PetsConfiguration {
}
