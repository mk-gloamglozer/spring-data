package com.mkdevs.domain;

import static lombok.AccessLevel.PRIVATE;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class BasicDice extends AbstractDice {

	@Column(name = "NUMBER_OF_SIDES")
	private int numberOfSides;

	@Transient
	private static final Random random = new Random();
	
	public BasicDice(String name, int numberOfSides) {
		super(name);
		this.numberOfSides = numberOfSides;
	}
	
	
	@Override
	public int roll() {
		return random.nextInt(numberOfSides)+1;
	}

	
	public static AbstractDice build(int numberOfSides, String name) {

		if(numberOfSides<1) {
			throw new IllegalArgumentException("number of sides must be greater than 0");
		}
		if(name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}

		return new BasicDice(name, numberOfSides);
	}


}
