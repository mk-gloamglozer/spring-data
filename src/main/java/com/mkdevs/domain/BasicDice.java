package com.mkdevs.domain;

import static lombok.AccessLevel.PRIVATE;

import java.util.Random;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = PRIVATE)
public class BasicDice implements Dice {
	
	private final int numberOfSides;
	private final String name;
	private static final Random random = new Random();
	
	@Override
	public int roll() {
		return random.nextInt(numberOfSides)+1;
	}

	@Override
	public int roll(int times) {
		int total = 0;
		for (int i = 0; i < times; i++) {
			total += this.roll();
		}
		return total;
	}
	
	public static Dice build(int numberOfSides, String name) {

		if(numberOfSides<1) {
			throw new IllegalArgumentException("number of sides must be greater than 0");
		}
		if(name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}

		return new BasicDice(numberOfSides, name);
	}

	@Override
	public String name() {
		return name;
	}

}
