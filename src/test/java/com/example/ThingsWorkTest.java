package com.example;

import com.example.pets.Cat;
import com.example.pets.CatRepository;
import com.example.pets.Dog;
import com.example.pets.DogRepository;
import com.example.plants.Fern;
import com.example.plants.FernRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class ThingsWorkTest {

	@Autowired DogRepository dogRepository;
	@Autowired CatRepository catRepository;
	@Autowired FernRepository fernRepository;

	@Test
	public void moarDogsPls() {
		assertThat(dogRepository.count()).isEqualTo(0);
		dogRepository.save(new Dog());
		assertThat(dogRepository.count()).isEqualTo(1);
	}

	@Test
	public void moarCatsPls() {
		assertThat(catRepository.count()).isEqualTo(0);
		catRepository.save(new Cat());
		assertThat(catRepository.count()).isEqualTo(1);
	}

	@Test
	public void moarPntsPls() {
		assertThat(fernRepository.count()).isEqualTo(0);
		fernRepository.save(new Fern());
		assertThat(fernRepository.count()).isEqualTo(1);
	}

}
