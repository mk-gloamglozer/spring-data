package com.mkdevs.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FixedDiceTest {

	@Test
	public void testRollReturnsNumberBetweenOneAndMax(){
		Dice dice = FixedDice.build(5, "name");
		assertThat(dice.roll()).isEqualTo(5);
		assertThat(dice.name()).isEqualTo("name");

	}
	
	
	@Test
	public void whenSidesLessThanOne_thenBuildThrowsIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> FixedDice.build(0, "name"));
	}
	
	@Test
	public void whenNameIsEmpty_thenBuildThrowsIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> FixedDice.build(5, ""));
	}
}
