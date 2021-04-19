package com.mkdevs.domain;

import org.springframework.stereotype.Component;

@Component
public class BasicDiceFactory implements DiceFactory<BasicDiceFactory.BasicTemplate> {

	@Override
	public Dice build(BasicTemplate template) {
		return new BasicDice(template.name(), template.numberOfSides());
	}

	
	public interface BasicTemplate{

		String name();

		int numberOfSides();
		
	}

}
