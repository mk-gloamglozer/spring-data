package com.mkdevs.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.mkdevs.io.UserIO;
import com.mkdevs.repository.DiceRepository;
import com.mkdevs.service.DiceService;
import com.mkdevs.service.DiceServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DiceServiceImplTest {
	
	@Mock DiceRepository repo;

	// spy allows use of default id method
	@Spy Dice dice;
	
	DiceService modService;
	
	@BeforeEach
	public void setup() {
		modService = new DiceServiceImpl(repo);
	}
	
	@Test
	public void testCreateDice() {
		when(repo.saveDice(dice)).thenReturn(Optional.of(dice));
		assertThat(modService.createDice(dice)).isEqualTo(dice);
	}
	
	@Test
	public void testRemoveDice() {
		when(repo.removeDice(dice)).thenReturn(Optional.of(dice));
		assertThat(modService.removeDice(dice)).isEqualTo(dice);
	}
	
	@Test
	public void testGetAllDice() {
		when(repo.findAll()).thenReturn(List.of(dice));
		assertThat(modService.getAllDice()).containsExactly(dice);
	}
}
