package com.mkdevs.interactiveInput;

import com.mkdevs.IDable;
import com.mkdevs.domain.Dice;

/** classes that implement this interface should 
 * be campable of generating a dice using the assemble method
 * @author mike
 *
 */
public interface DiceAssembler extends IDable{

	/**
	 * Assembles and returns a dice
	 */
	Dice assemble();

}