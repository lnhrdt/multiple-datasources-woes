package com.example.plants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fern {
    @Id
    @GeneratedValue
    Long id;
}
