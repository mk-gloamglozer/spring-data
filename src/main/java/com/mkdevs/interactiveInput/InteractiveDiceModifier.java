package com.mkdevs.interactiveInput;

import com.mkdevs.domain.Dice;

public interface InteractiveDiceModifier {

	Dice createDice();
	Dice removeDice();

}
