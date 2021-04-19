package com.mkdevs.interactiveInput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.Dice;
import com.mkdevs.interactiveInput.AssemblerStore;
import com.mkdevs.interactiveInput.DiceAssembler;
import com.mkdevs.interactiveInput.InteractiveDiceModifierImpl;
import com.mkdevs.io.UserIO;
import com.mkdevs.service.DiceService;

@ExtendWith(MockitoExtension.class)
public class InteractiveDiceModifierImplTest {
	
	@Mock
	AssemblerStore assemblerStore;
	
	@Mock
	DiceService diceService;
	
	@Mock
	DiceAssembler assembler;
	
	@Mock
	UserIO userio;
	
	@Mock
	Dice dice;
	
	@InjectMocks
	InteractiveDiceModifierImpl modder;
	
	@Test
	public void testCreateDice() {
		when(assemblerStore.allAssemblers()).thenReturn(List.of(assembler));
		when(assembler.id()).thenReturn(assemblerId());
		when(assembler.assemble()).thenReturn(dice);
		when(diceService.createDice(dice)).thenReturn(dice);
		when(userio.getIntegerInput(anyString())).thenReturn(0);
		assertThat(modder.createDice()).isEqualTo(dice);
	}
	
	@Test
	public void testRemoveDice() {
		when(diceService.getAllDice()).thenReturn(List.of(dice));
		when(userio.getIntegerInput(anyString())).thenReturn(0);
		when(diceService.removeDice(dice)).thenReturn(dice);
		
		assertThat(modder.removeDice()).isEqualTo(dice);
	}

	private String assemblerId() {
		return "id";
	}
}
