package com.mkdevs.stdinput;

import java.util.List;

/**
 * Stores a list of Dice assemblers availible
 * to the application.
 * @author mike
 *
 */
public interface AssemblerStore {
	
	List<DiceAssembler> allAssemblers();

}
