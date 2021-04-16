package com.mkdevs.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.Dice;
import com.mkdevs.domain.DiceFactory;
import com.mkdevs.domain.DiceFactory.DiceType;
import com.mkdevs.io.UserIO;
import com.mkdevs.repository.DiceRepository;
import com.mkdevs.service.DiceModificationService;
import com.mkdevs.service.DiceModificationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DiceModificationServiceImplTest {
	
	@Mock UserIO userio;
	@Mock DiceRepository repo;
	@Mock DiceFactory factory;
	@Mock DiceType type;

	// spy allows use of default id method
	@Spy Dice dice;
	
	DiceModificationService modService;
	
	@BeforeEach
	public void setup() {
		modService = new DiceModificationServiceImpl(factory, repo, userio);
	}
	
	@Test
	public void createDiceGeneratesAndSavesDice() {
		when(factory.availibleTypes()).thenReturn(List.of(type));
		when(type.id()).thenReturn("type");
		when(userio.getIntegerInput()).thenReturn(0);
		when(factory.createDice(anyString(), anyString())).thenReturn(dice);
		when(repo.saveDice(dice)).thenReturn(Optional.of(dice));
		when(userio.getStringInput()).thenReturn("name");
		
		modService.createDice();
		
		verify(factory).createDice("type", "name");
		verify(repo).saveDice(dice);
	}
	
	@Test
	public void removeDiceRemovesTheCorrectDice() {
		when(repo.findAll()).thenReturn(List.of(dice));
		when(dice.name()).thenReturn("name");
		when(userio.getIntegerInput()).thenReturn(0);
		when(repo.removeDice(dice)).thenReturn(Optional.of(dice));
		
		modService.removeDice();
		
		verify(repo).removeDice(dice);
	}
}
