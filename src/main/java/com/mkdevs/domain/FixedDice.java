package com.mkdevs.domain;

import static lombok.AccessLevel.PRIVATE;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PRIVATE)
public class FixedDice implements Dice {
	
	private final int fixed;
	private final String name;

	@Override
	public int roll() {
		return fixed;
	}

	@Override
	public int roll(int times) {
		return fixed * times;
	}

	@Override
	public String name() {
		return name;
	}
	
	public static Dice build(int fix, String name) {
		if (fix <1) {
			throw new IllegalArgumentException("Fixed number must be greater than 1");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("The name of the dice cannot be empty");
		}
		
		return new FixedDice(fix, name);
	}
	
}
