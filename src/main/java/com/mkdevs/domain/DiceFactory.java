package com.mkdevs.domain;

public interface DiceFactory<T> {
	/**
	 * Builds the dice object from template
	 * 
	 * @throws UnsupportedOperationException if template class unsuported
	 * @param template used to build Dice
	 * @return a Dice instance
	 */
	Dice build(T template);
	
}
