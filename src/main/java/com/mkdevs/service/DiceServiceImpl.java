package com.mkdevs.service;

import static com.mkdevs.utils.IOUtil.generateNumberMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkdevs.domain.Dice;
import com.mkdevs.io.UserIO;
import com.mkdevs.repository.DiceRepository;
import com.mkdevs.repository.DiceStorageException;
import com.mkdevs.utils.IOUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DiceServiceImpl implements DiceService{
	
	@Autowired
	private DiceRepository diceRepo;
	
	
	@Override
	public Dice createDice(Dice newDice) {
		return diceRepo.saveDice(newDice).orElseThrow();
	}

	@Override
	public Dice removeDice(Dice toRemove) {
		return diceRepo.removeDice(toRemove).orElseThrow(() -> new IllegalArgumentException("The dice was not found in the store"));
	}

	@Override
	public List<Dice> getAllDice() {
		return diceRepo.findAll();
	}

}
