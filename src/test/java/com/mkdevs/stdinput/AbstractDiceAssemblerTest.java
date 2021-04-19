package com.mkdevs.stdinput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.Dice;
import com.mkdevs.domain.DiceFactory;

@ExtendWith(MockitoExtension.class)
public class AbstractDiceAssemblerTest {
	
	@Mock DiceFactory<Object> diceFactory;
	
	@Mock Object template;
	
	@Mock Dice dice;

	AbstractDiceAssembler<Object> assembler;
	
	@BeforeEach
	public void setup() {
		assembler = new AbstractDiceAssembler<Object>(diceFactory) {
			
			@Override
			public String id() {
				return null;
			}
			
			@Override
			protected Object generateTemplate() {
				return template;
			}
		};
	}

	@Test
	public void testAssemble() throws Exception {
		when(diceFactory.build(template)).thenReturn(dice);
		assertThat(assembler.assemble()).isEqualTo(dice);
	}

}
