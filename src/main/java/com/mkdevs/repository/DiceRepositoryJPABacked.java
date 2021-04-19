package com.mkdevs.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
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
			throw new DiceStorageException("The supplied dice cannot be saved as it cannot be cast as an abstact dice",e);
		}catch (HibernateException e) {
			throw new DiceStorageException("The supplied dice cannot be saved due to a hibernate error",e);
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
