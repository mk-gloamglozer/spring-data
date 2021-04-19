package com.mkdevs.interactiveInput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.Dice;
import com.mkdevs.interactiveInput.DiceInputService;
import com.mkdevs.interactiveInput.DiceInputServiceStdInput;
import com.mkdevs.io.UserIO;
import com.mkdevs.repository.DiceRepository;
import com.mkdevs.utils.IOUtil.ErrorMessage;

@ExtendWith(MockitoExtension.class)
public class DiceInputServiceStdInputTest {
	
	// spy allows use of default id method.
	@Spy Dice dice;
	@Mock DiceRepository repo;
	@Mock UserIO inputer;

	DiceInputService inputService;	

	@BeforeEach
	public void setup() {
		inputService = new DiceInputServiceStdInput(repo, inputer);
	}
	
	@Test
	public void whenDiceSelected_thenReturned() {
		when(repo.findAll()).thenReturn(List.of(dice));
		when(dice.name()).thenReturn("name");
		when(inputer.getIntegerInput(anyString())).thenReturn(0);
		when(inputer.isYesInput(anyString())).thenReturn(false);
		
		assertThat(inputService.makeDiceSelection()).contains(dice);
	}
	
	@Test
	public void whenMultipleDiceSelected_thenAllReturned() {
		when(repo.findAll()).thenReturn(List.of(dice));
		when(dice.name()).thenReturn("name");
		when(inputer.getIntegerInput(anyString())).thenReturn(0);
		when(inputer.isYesInput(anyString())).thenReturn(true, false);
		
		assertThat(inputService.makeDiceSelection()).hasSize(2).contains(dice);
		
	}
	
	@Test
	public void whenNonExistantSelectionMade_thenErrorMessagePrinted() {
		
		when(repo.findAll()).thenReturn(List.of(dice));
		when(dice.name()).thenReturn("name");
		when(inputer.getIntegerInput(anyString())).thenReturn(1,0);
		when(inputer.isYesInput(anyString())).thenReturn(false);
		
		assertThat(inputService.makeDiceSelection()).hasSize(1).contains(dice);
		
		verify(inputer).writeln(ErrorMessage.INVALID_SELECTION.message());
	}

}
