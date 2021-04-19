package com.mkdevs.stdinput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mkdevs.domain.Dice;
import com.mkdevs.domain.DiceFactory;
import com.mkdevs.domain.FixedDiceFactory.FixedDiceTemplate;
import com.mkdevs.io.UserIO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
public class FixedDiceAssembler extends AbstractDiceAssembler<FixedDiceTemplate> {
	
	private final UserIO userio;
	
	@Autowired
	public FixedDiceAssembler(DiceFactory<FixedDiceTemplate> factory, UserIO userio) {
		super(factory);
		this.userio = userio;
	}

	@Override
	public String id() {
		return "Fixed Dice";
	}

	@Override
	protected FixedDiceTemplate generateTemplate() {
		return new FixedDiceTemplateImpl(userio.getStringInput("Name for dice"), userio.getIntegerInput("Fixed number for dice"));
	}

	@RequiredArgsConstructor
	private static class FixedDiceTemplateImpl implements FixedDiceTemplate{
		
		private final String name;
		private final int fixed;

		@Override
		public int fix() {
			return fixed;
		}

		@Override
		public String name() {
			return name;
		}
		
	}
	
	
	

}
