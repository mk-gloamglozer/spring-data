package com.mkdevs;

import java.util.Collection;
import java.util.stream.Collectors;

public interface DiceCalcUtil {
	
	default int getTotal(Collection<Dice> diceCollection) {
		return diceCollection.stream().collect(Collectors.summingInt(Dice::roll));
	}

}
