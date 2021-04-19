package com.mkdevs.domain;

import org.springframework.stereotype.Component;

@Component
public class FixedDiceFactory implements DiceFactory<FixedDiceFactory.FixedDiceTemplate> {

	@Override
	public Dice build(FixedDiceTemplate template) {

		return FixedDice.build(template.fix(), template.name());
	}

	
	public interface FixedDiceTemplate {

		int fix();

		String name();
		
	}

}
