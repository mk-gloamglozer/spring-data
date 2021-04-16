package com.mkdevs.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mkdevs.domain.Dice;

/**
 * In memory repository for dice
 * @author mike
 *
 */

public class DiceRepositoryImpl implements DiceRepository{
	
	private final Map<String,Dice> diceCollection = new HashMap<String, Dice>();

	@Override
	public List<Dice> findAll() {
		return diceCollection.values().stream().collect(Collectors.toList());
	}

	@Override
	public Optional<Dice> findByName(String name) {
			return Optional.ofNullable(diceCollection.get(name));
	}

	@Override
	public Optional<Dice> saveDice(Dice dice) {
		if (dice.name().isEmpty()) {
			throw new IllegalArgumentException("Cannot add a dice with an empty name");
		}
		diceCollection.put(dice.name(), dice);
		return Optional.of(dice);
	}

	@Override
	public Optional<Dice> removeDice(Dice dice) {
		return Optional.ofNullable(diceCollection.remove(dice.name()));
	}

}
