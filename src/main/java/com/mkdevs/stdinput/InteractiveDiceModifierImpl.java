package com.mkdevs.stdinput;

import static com.mkdevs.utils.IOUtil.generateNumberMap;
import static com.mkdevs.utils.IOUtil.getIDableFomMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkdevs.domain.Dice;
import com.mkdevs.io.UserIO;
import com.mkdevs.service.DiceService;
import com.mkdevs.utils.IOUtil;

@Service
public class InteractiveDiceModifierImpl implements InteractiveDiceModifier {

	@Autowired 
	private AssemblerStore assemblerStore;

	@Autowired 
	private DiceService modder;
	
	@Autowired 
	private UserIO userio;

	@Override
	public Dice createDice() {
		userio.writeln("Select one of these archetypes");
		DiceAssembler assembler = getIDableFomMap(
				generateNumberMap(assemblerStore.allAssemblers()), 
				userio);
		return modder.createDice(assembler.assemble());
	}

	@Override
	public Dice removeDice() {
		userio.writeln("Select the dice you wish to delete");
		Dice toRemove = getIDableFomMap(
				generateNumberMap(modder.getAllDice()), 
				userio);
		return modder.removeDice(toRemove);
	}

}
