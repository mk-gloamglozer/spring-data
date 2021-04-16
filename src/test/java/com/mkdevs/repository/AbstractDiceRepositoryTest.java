package com.mkdevs.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mkdevs.domain.AbstractDice;
import com.mkdevs.domain.BasicDice;
import com.mkdevs.domain.FixedDice;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AbstractDiceRepositoryTest {
	
	@Autowired
	AbstractDiceRepository repo;
	
	@Test
	public void testDiceCanBePersisted() {
		AbstractDice dice = BasicDice.build(20, "name");
		repo.save(dice);
		assertThat(repo.count()).isEqualTo(1);
	}
	
	@Test
	public void whenDiceInRepo_thenCanBeFoundByName() {
		AbstractDice dice = FixedDice.build(20, "name");
		repo.save(dice);
		assertThat(repo.findByName(dice.name())).isNotEmpty();
		assertThat(repo.findByName("name").get().roll()).isEqualTo(20);
	}
	
	
	
	@Test
	public void whenDiceInRepo_thenExistsByName() {
		AbstractDice dice = BasicDice.build(20, "name");
		repo.save(dice);
		assertThat(repo.existsByName("name")).isTrue();
	}
	
}
