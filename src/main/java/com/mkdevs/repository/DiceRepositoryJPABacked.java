package com.mkdevs.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.mkdevs.domain.AbstractDice;
import com.mkdevs.domain.Dice;

@Repository
@Profile("jpa")
@Transactional
public class DiceRepositoryJPABacked implements DiceRepository {
	
	@Autowired
	AbstractDiceRepository internalRepo;

	@Override
	public List<Dice> findAll() {
		return internalRepo
				.findAll()
				.stream()
				.map((dice) -> dice)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Dice> findByName(String name) {
		return internalRepo.findByName(name).flatMap(Optional::ofNullable);
	}

	@Override
	public Optional<Dice> saveDice(Dice dice) {

		try {
			return Optional.of(internalRepo.save((AbstractDice) dice));
		}catch (ClassCastException e) {
			throw new IllegalArgumentException("The supplied dice cannot be saved");
		}
	}

	@Override
	public Optional<Dice> removeDice(Dice dice) {
		if(internalRepo.existsByName(dice.name())) {
			internalRepo.deleteByName(dice.name());
			return Optional.of(dice);
		}
		return Optional.empty();

	}

}
