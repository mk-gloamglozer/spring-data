package com.mkdevs.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DiceFactoryImpl implements DiceFactory {

	@Override
	public Dice createDice(String type, String name) {
		DiceTypeEnum diceType;
		try {
			diceType = DiceTypeEnum.valueOf(type);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("The given dice type: " + type + " is not valid", e);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("The type cannot be null");
		}
		
		return diceType.generate(name);

	}

	@Override
	public List<DiceType> availibleTypes() {
		return Arrays.asList(DiceTypeEnum.values())
				.stream()
				.collect(Collectors.toList());
	}
	
	private enum DiceTypeEnum implements DiceType{
		D6((name) -> BasicDice.build(6, name)),
		D20((name) -> BasicDice.build(20, name)),
		FixedD20((name) -> FixedDice.build(20,name));
		
		private Function<String, Dice> generator; 

		DiceTypeEnum(Function<String, Dice> diceGenerator) {
			this.generator = diceGenerator;
		}
		
		public Dice generate(String name) {
			return generator.apply(name);
		}

		@Override
		public String id() {
			return this.name();
		}
		
		
	}

}
