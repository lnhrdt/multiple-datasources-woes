package com.example.plants;

import com.example.pets.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FernRepository extends CrudRepository<Fern, Long> {
}
