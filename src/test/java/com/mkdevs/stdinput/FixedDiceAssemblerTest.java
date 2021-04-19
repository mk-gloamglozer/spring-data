package com.mkdevs.stdinput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.DiceFactory;
import com.mkdevs.domain.FixedDiceFactory.FixedDiceTemplate;
import com.mkdevs.io.UserIO;

@ExtendWith(MockitoExtension.class)
public class FixedDiceAssemblerTest {
	
	@Mock DiceFactory<FixedDiceTemplate> factory; 
	
	@Mock UserIO userio;
	
	@InjectMocks FixedDiceAssembler assembler;

	@Test
	public void testGenerateTemplate() throws Exception {
		when(userio.getStringInput(anyString())).thenReturn(name());
		when(userio.getIntegerInput(anyString())).thenReturn(fix());
		assertThat(assembler.generateTemplate()).matches(desiredDice());
	}

	private Predicate<? super FixedDiceTemplate> desiredDice() {
		return (template) -> template.name() == name() &&
				template.fix() == fix();
	}

	private String name() {
		return "name";
	}

	private int fix() {
		return 0;
	}
	

}
