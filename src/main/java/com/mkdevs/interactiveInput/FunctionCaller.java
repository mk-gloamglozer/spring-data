package com.mkdevs.interactiveInput;

import static com.mkdevs.utils.IOUtil.generateNumberMap;
import static com.mkdevs.utils.IOUtil.getIDableFomMap;
import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mkdevs.IDable;
import com.mkdevs.domain.Dice;
import com.mkdevs.io.UserIO;
import com.mkdevs.service.DiceService;
import com.mkdevs.utils.DiceCalcUtil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Service
public class FunctionCaller {

	@Autowired
	private DiceInputService diceService;
	@Autowired
	private DiceCalcUtil calcUtil;
	@Autowired
	private UserIO userio;
	@Autowired
	private InteractiveDiceModifier modService;

	@Autowired @Getter(value = PROTECTED)
	private List<FunctionOption> options;
	
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
	
	@Component(value = "RollDice")
	@NoArgsConstructor
	@AllArgsConstructor
	static class RollDice implements FunctionOption{
		
		@Autowired
		private DiceInputService diceService;
		@Autowired
		private DiceCalcUtil calcUtil;
		@Autowired
		private UserIO userIO;
		
		@Override
		public void doIt() {
			Collection<Dice> diceCollection = diceService.makeDiceSelection();
			Integer sum = calcUtil.getTotal(diceCollection);
			userIO.writeln("You rolled: "+ sum.toString() +"!");
			
		}

		@Override
		public String id() {
			return "Roll Dice";
		}
		
	}
	
	@Component(value = "AddDice")
	@NoArgsConstructor
	@AllArgsConstructor
	static class AddDice implements FunctionOption{
		
		@Autowired
		private InteractiveDiceModifier modService;
		@Autowired
		private UserIO userio;
		
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
	
	@Component(value = "RemoveDice")
	@NoArgsConstructor
	@AllArgsConstructor
	static class RemoveDice implements FunctionOption{
		
		@Autowired
		private InteractiveDiceModifier modService;
		@Autowired
		private UserIO userio;

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
