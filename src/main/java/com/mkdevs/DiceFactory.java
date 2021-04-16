package com.mkdevs;

import java.util.List;

import com.mkdevs.DiceFactory.DiceType;

public interface DiceFactory {

	public interface DiceType extends IDable{
	}

	Dice createDice(String type, String name)
		throws IllegalArgumentException;

	List<DiceType> availibleTypes();
}
