package com.mkdevs.service;

import java.util.List;

import com.mkdevs.domain.Dice;

/**
 * Service for aquiring user input to save and remove
 * dice from the store
 * @author mike
 *
 */
public interface DiceService {

	/**
	 * Get user input and use it to generate and store a dice 
	 * object. This object is then returned. 
	 * @param newDice TODO
	 * @return
	 */
	Dice createDice(Dice newDice);
	
	/**
	 * Get user input and use it to remove a dice from the 
	 * store that is then returned
	 * @param toRemove TODO
	 * @return
	 */
	Dice removeDice(Dice toRemove);
	
	List<Dice> getAllDice();
}
