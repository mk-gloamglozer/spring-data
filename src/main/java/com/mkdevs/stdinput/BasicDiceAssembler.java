package com.mkdevs.stdinput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mkdevs.domain.BasicDiceFactory.BasicTemplate;
import com.mkdevs.domain.Dice;
import com.mkdevs.domain.DiceFactory;
import com.mkdevs.io.UserIO;

import lombok.RequiredArgsConstructor;

@Component
public class BasicDiceAssembler extends AbstractDiceAssembler<BasicTemplate>{

	private final UserIO userio;
	
	@Autowired
	public BasicDiceAssembler(DiceFactory<BasicTemplate> factory, UserIO userio) {
		super(factory);
		this.userio = userio;
	}


	@Override
	public String id() {
		return "Basic Dice";
	}

	
	@Override
	protected BasicTemplate generateTemplate() {
		return new BasicTemplateImpl(
				userio.getStringInput("Enter dice name"), 
				userio.getIntegerInput("Enter number of sides"));
	}

	@RequiredArgsConstructor
	private class BasicTemplateImpl implements BasicTemplate{

		private final String name;
		private final int sides;

		@Override
		public String name() {
			return name;
		}

		@Override
		public int numberOfSides() {
			return sides;
		}
		
	}



}
