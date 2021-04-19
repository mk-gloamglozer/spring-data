package com.mkdevs.interactiveInput;

import java.util.List;

import com.mkdevs.domain.Dice;

public interface DiceInputService {

	/**
	 * Returns a list of dice selected by the user
	 * @return
	 */
	List<Dice> makeDiceSelection();
}
