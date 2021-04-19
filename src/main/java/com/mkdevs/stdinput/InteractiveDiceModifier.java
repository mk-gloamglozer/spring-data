package com.mkdevs.stdinput;

import com.mkdevs.domain.Dice;

public interface InteractiveDiceModifier {

	Dice createDice();
	Dice removeDice();

}
