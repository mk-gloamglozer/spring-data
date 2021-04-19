package com.mkdevs.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.BasicDiceFactory.BasicTemplate;

@ExtendWith(MockitoExtension.class)
public class BasicDiceFactoryTest {

	@Mock BasicTemplate template;
	
	BasicDiceFactory basicDiceFactory;
	
	@BeforeEach
	public void setup() {
		basicDiceFactory = new BasicDiceFactory();
	}
	
	@Test
	public void testBuild() throws Exception {
		when(template.name()).thenReturn(diceName());
		when(template.numberOfSides()).thenReturn(diceNumberOfSides());
		
		assertThat(basicDiceFactory.build(template)).matches(requestedDice());
	}

	private Predicate<? super Dice> requestedDice() {
		return (dice) -> {
			return dice.name().equals(diceName()) && dice.roll() > 0;
		};
	}

	private Integer diceNumberOfSides() {
		return 6;
	}

	private String diceName() {
		return "D6";
	}

}
