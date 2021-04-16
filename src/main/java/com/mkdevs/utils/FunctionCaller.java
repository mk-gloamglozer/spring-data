package com.mkdevs.utils;

import static com.mkdevs.utils.IOUtil.generateNumberMap;
import static com.mkdevs.utils.IOUtil.getIDableFomMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.mkdevs.IDable;
import com.mkdevs.domain.Dice;
import com.mkdevs.io.UserIO;
import com.mkdevs.service.DiceInputService;
import com.mkdevs.service.DiceModificationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FunctionCaller {
	
	private DiceInputService diceService;
	private DiceCalcUtil calcUtil;
	private UserIO userio;
	private DiceModificationService modService;
	private List<FunctionOption> options;

	public FunctionCaller(DiceInputService diceService, DiceCalcUtil calcUtil, UserIO userio,
			DiceModificationService modService) {
		this.diceService = diceService;
		this.calcUtil = calcUtil;
		this.userio = userio;
		this.modService = modService;
		
		//TODO move to its own store
		options = new ArrayList<FunctionCaller.FunctionOption>();
		options.add(new RollDice(diceService, calcUtil, userio));
		options.add(new AddDice(modService, userio));
		options.add(new RemoveDice(modService, userio));
					
	}
	/**
	 * asks the user what function to call and calls that 
	 * function. 
	 */
	public void askAndCall() {
		FunctionOption functionOption = getIDableFomMap(generateNumberMap(options), userio);
		functionOption.doIt();
	}

	public void addFunction(FunctionOption option) {
		this.options.add(option);
	}

	public interface FunctionOption extends IDable{
		void doIt();
	}
	
	@AllArgsConstructor
	private class RollDice implements FunctionOption{
		
		DiceInputService diceService;
		DiceCalcUtil calcUtil;
		UserIO userIO;
		
		@Override
		public void doIt() {
			Collection<Dice> diceCollection = diceService.makeDiceSelection();
			Integer sum = calcUtil.getTotal(diceCollection);
			userIO.writeln("You rolled: "+ sum.toString() +"!");
			// TODO Auto-generated method stub
			
		}

		@Override
		public String id() {
			return "Roll Dice";
		}
		
	}
	
	@AllArgsConstructor
	private class AddDice implements FunctionOption{
		
		DiceModificationService modService;
		UserIO userio;
		
		@Override
		public void doIt() {
			Dice dice = modService.createDice();
			userio.writeln("you created a dice with the name: " + dice.name());
		}

		@Override
		public String id() {
			return "Add a Dice";
		}
	}
	
	@AllArgsConstructor
	private class RemoveDice implements FunctionOption{
		
		DiceModificationService modService;
		UserIO userio;

		@Override
		public void doIt() {
			Dice dice = modService.removeDice();
			userio.writeln("you removed the dice named: " + dice.name());
		}

		@Override
		public String id() {
			return "Remove a Dice";
		}
	}
}
