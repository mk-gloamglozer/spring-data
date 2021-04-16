package com.mkdevs;

import java.util.List;
import java.util.Optional;

/**
 * Stores the dice within the application
 * @author mike
 *
 */
public interface DiceRepository {
	
	/**
	 * return all dice within the repository
	 * @return
	 */
	List<Dice> findAll();

	/**
	 * Return the dice with the given name if it exits else return empty 
	 * optional
	 */
	Optional<Dice> findByName(String name);
	
	
	/**
	 * Add the dice to the repository and return the 
	 * saved dice.  
	 */
	Optional<Dice> saveDice(Dice dice);

	/**
	 * Remove the dice if it exists otherwise return empty optional
	 * @param dice
	 * @return
	 */
	Optional<Dice> removeDice(Dice dice);
}
