package com.mkdevs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DiceCalcUtilImplTest {
	
	@Mock Dice dice;
	
	@Test
	public void testTotalIsCorrect() {
		when(dice.roll()).thenReturn(5);
		
		List<Dice> diceCollection = List.of(dice,dice,dice);
		DiceCalcUtil calc = new DiceCalcUtil() {};
		assertThat(calc.getTotal(diceCollection)).isEqualTo(15);
	}
}
