package com.mkdevs.stdinput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.BasicDiceFactory.BasicTemplate;
import com.mkdevs.io.UserIO;

@ExtendWith(MockitoExtension.class)
public class BasicDiceAssemblerTest {
	
	@Mock UserIO userio;
	@InjectMocks BasicDiceAssembler assembler;
	
	@Test
	public void testGeneratesTemplate() {
		when(userio.getStringInput(any())).thenReturn("name");
		when(userio.getIntegerInput(any())).thenReturn(1);
		assertThat(assembler.generateTemplate())
			.matches(requestedTemplate());
	}
	

	private Predicate<? super BasicTemplate> requestedTemplate() {
		return (template) -> template.name().equals("name") &&
				template.numberOfSides() ==1;
	}
	

}
