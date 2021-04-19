package com.mkdevs.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.FixedDiceFactory.FixedDiceTemplate;

@ExtendWith(MockitoExtension.class)
public class FixedDiceFactoryTest {
	@Mock FixedDiceTemplate template;
	
	FixedDiceFactory factory;
	
	@BeforeEach
	public void setup() {
		factory = new FixedDiceFactory();
	}
	
	@Test
	public void testBuildMethod() {
		when(template.name()).thenReturn(diceName());
		when(template.fix()).thenReturn(fix());
		assertThat(factory.build(template)).matches(requestedDice());
	}

	private Predicate<? super Dice> requestedDice() {
		return (dice) -> dice.name().equals(diceName()) &&
				dice.roll() == fix();
	}

	private Integer fix() {
		return 5;
	}

	private String diceName() {
		return "name";
	}
}
