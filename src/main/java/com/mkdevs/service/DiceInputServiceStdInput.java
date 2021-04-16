package com.mkdevs.service;

import static com.mkdevs.utils.IOUtil.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkdevs.domain.Dice;
import com.mkdevs.io.UserIO;
import com.mkdevs.repository.DiceRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class DiceInputServiceStdInput implements DiceInputService {
	
	@Autowired
	private DiceRepository diceRepo;
	
	@Autowired
	private UserIO inputer;
	
	@Override
	public List<Dice> makeDiceSelection() {
		inputer.writeln("Welcome! Please select a dice from the list");
		List<Dice> diceOutList = new ArrayList<Dice>();
		Map<Integer, Dice> diceMap = generateNumberMap(diceRepo.findAll());

		while(true) {
			diceOutList.add(getIDableFomMap(diceMap, inputer));
			inputer.writeln("Add another? (y/n): ");
			if (!inputer.isYesInput()) {
				return diceOutList;
			}
			
		}
	}
	

}
