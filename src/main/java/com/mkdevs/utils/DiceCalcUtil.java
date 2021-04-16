package com.mkdevs.utils;

import java.util.Collection;
import java.util.stream.Collectors;

import com.mkdevs.domain.Dice;

public interface DiceCalcUtil {
	
	default int getTotal(Collection<Dice> diceCollection) {
		return diceCollection.stream().collect(Collectors.summingInt(Dice::roll));
	}

}
