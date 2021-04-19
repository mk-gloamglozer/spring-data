package com.mkdevs.domain;

import static lombok.AccessLevel.PRIVATE;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
public class FixedDice extends AbstractDice{
	
	@Column(name = "FIXED")
	private int fixed;

	public FixedDice(String name, int fixed) {
		super(name);
		this.fixed = fixed;
	}

	@Override
	public int roll() {
		return fixed;
	}

	
	public static AbstractDice build(int fix, String name) {
		if (fix <1) {
			throw new IllegalArgumentException("Fixed number must be greater than 1");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("The name of the dice cannot be empty");
		}
		
		return new FixedDice(name, fix);
	}
	
}
