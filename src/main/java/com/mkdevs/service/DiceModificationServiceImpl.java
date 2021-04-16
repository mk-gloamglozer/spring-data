package com.mkdevs.service;

import static com.mkdevs.utils.IOUtil.generateNumberMap;

import java.util.Map;

import com.mkdevs.domain.Dice;
import com.mkdevs.domain.DiceFactory;
import com.mkdevs.domain.DiceFactory.DiceType;
import com.mkdevs.io.UserIO;
import com.mkdevs.repository.DiceRepository;
import com.mkdevs.utils.IOUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DiceModificationServiceImpl implements DiceModificationService{
	
	private DiceFactory diceFactory;
	
	private DiceRepository diceRepo;
	
	private UserIO userIO;
	
	@Override
	public Dice createDice() {
		Map<Integer, DiceType> availableTypeMap = generateNumberMap(diceFactory.availibleTypes());
		boolean shouldContinue = true;
		while(shouldContinue) {
			DiceType diceType = IOUtil.getIDableFomMap(availableTypeMap, userIO);
			try {
				Dice dice = diceFactory.createDice(diceType.id(), getNameFromUser());
				return diceRepo.saveDice(dice).get();
			} catch (IllegalArgumentException e) {
				userIO.writeln("There was a problem with creating the dice: "+e.getMessage());
				userIO.write("would you like to try again? (y/n)");
				shouldContinue = userIO.isYesInput();
			}
		}
		
		// nothing created 
		return null;
	}

	@Override
	public Dice removeDice() {
		Map<Integer, Dice> availableDiceMap = generateNumberMap(diceRepo.findAll());
		
		boolean shouldContinue = true;

		Dice dice = IOUtil.getIDableFomMap(availableDiceMap, userIO);

		return diceRepo.removeDice(dice).orElseThrow(() -> new IllegalArgumentException("The dice was not found in the store"));
	}

	private String getNameFromUser() {
		userIO.write("Name for the new dice: ");
		return userIO.getStringInput();
	}
}
