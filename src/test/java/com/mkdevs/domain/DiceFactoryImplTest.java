package com.mkdevs.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mkdevs.domain.DiceFactory;
import com.mkdevs.domain.DiceFactoryImpl;

public class DiceFactoryImplTest {
	DiceFactory factory;
	
	@BeforeEach
	public void setup() {
		factory = new DiceFactoryImpl();
	}

	@Test
	public void testFactoryCanCreateD6() {

		assertThat(factory.createDice("D6", "name").name()).isEqualTo("name");
	}
	
	@Test
	public void testFactoryReturnsAllOptions() {
		assertThat(factory.availibleTypes()).hasSize(3).anyMatch((dicetype) -> dicetype.id() == "D6");
	}

}
