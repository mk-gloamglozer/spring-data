package com.mkdevs.interactiveInput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mkdevs.BeanConfig;
import com.mkdevs.domain.Dice;
import com.mkdevs.interactiveInput.DiceInputService;
import com.mkdevs.interactiveInput.FunctionCaller;
import com.mkdevs.utils.DiceCalcUtil;
import com.mkdevs.interactiveInput.FunctionCaller.AddDice;
import com.mkdevs.interactiveInput.FunctionCaller.FunctionOption;
import com.mkdevs.interactiveInput.FunctionCaller.RemoveDice;
import com.mkdevs.interactiveInput.FunctionCaller.RollDice;
import com.mkdevs.io.UserIO;

public class FunctionCallerTest {
	
	@Nested
	@ExtendWith(SpringExtension.class)
	@SpringBootTest(classes = FunctionCallerTest.Config.class)
//	@Disabled
	public class DependencyInjectionTests{
		@MockBean
		private DiceInputService diceService;
		@MockBean
		private DiceCalcUtil calcUtil;
		@MockBean
		private UserIO userio;
		@MockBean
		private InteractiveDiceModifier modService;
		
		@Autowired
		FunctionCaller caller;
		
		@Test
		public void testContextLoads() {
			assertThat(true).isTrue();
		}
		
		@Test
		public void testOptionListIsPopulated() {
			assertThat(caller.getOptions()).hasSize(3);
		}
		
		
	}
	
	@Nested
	@ExtendWith(MockitoExtension.class)
	public class AskAndCallTests{
		@Mock 
		DiceInputService diceService;
		@Mock 
		DiceCalcUtil calcUtil;
		@Mock 
		UserIO userio;
		@Mock 
		InteractiveDiceModifier modService;
		
		@Spy
		List<FunctionOption> options = new ArrayList<FunctionOption>();
		
		@Mock 
		FunctionOption option;
		
		@InjectMocks
		FunctionCaller caller;
		
		@BeforeEach
		public void setup() {
			options.clear();
			options.add(option);
		}
		
		@Test
		public void testOptionsNotEmpty() {
			assertThat(caller.getOptions()).isNotEmpty();
		}
		
		@Test
		public void testAskAndCall_willCallOption() {
			when(userio.getIntegerInput(anyString())).thenReturn(0);
			caller.askAndCall();
			verify(option).doIt();
		}
	}
	
	@Nested
	@ExtendWith(MockitoExtension.class)
	public class AddDiceTest{
		@Mock(answer = RETURNS_DEEP_STUBS)
		private InteractiveDiceModifier modService;
		@Mock
		private UserIO userio;
		
		private AddDice addDice;
		
		@BeforeEach
		public void setup() {
			addDice = new AddDice(modService, userio);
		}
		
		@Test
		public void testIdNotEmpty() {
			assertThat(addDice.id()).isNotEmpty();
		}
		
		@Test
		public void testDoIt_callsCreateDice() {
			addDice.doIt();
			verify(modService).createDice();
		}
	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	public class RemoveDiceTest {

		@Mock(answer = RETURNS_DEEP_STUBS)
		private InteractiveDiceModifier modService;
		@Mock
		private UserIO userio;
		
		private RemoveDice removeDice;
		
		@BeforeEach
		public void setup() {
			removeDice = new RemoveDice(modService, userio);
		}
		
		@Test
		public void testIdNotEmpty() {
			assertThat(removeDice.id()).isNotEmpty();
		}
		
		@Test
		public void testDoIt_callsRemoveDice() {
			removeDice.doIt();
			verify(modService).removeDice();
		}
	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	public class RollDiceTest {

		@Mock(answer = RETURNS_DEEP_STUBS)
		private DiceInputService diceService;
		@Mock
		private DiceCalcUtil calcUtil;
		@Mock
		private UserIO userIO;

		@Captor
		ArgumentCaptor<List<Dice>> listCaptor;

		@Mock
		Dice dice;

		private RollDice rollDice;
		
		@BeforeEach
		public void setup() {
			rollDice = new RollDice(diceService,calcUtil,userIO);
		}
		
		@Test
		public void testIdNotEmpty() {
			assertThat(rollDice.id()).isNotEmpty();
		}
		
		@Test
		public void testDoIt_callsRemoveDice() {
			when(diceService.makeDiceSelection()).thenReturn(List.of(dice));
			rollDice.doIt();
			verify(diceService).makeDiceSelection();
			verify(calcUtil).getTotal(listCaptor.capture());
			
			assertThat(listCaptor.getValue()).containsExactly(dice);
		}
	}

	@Configuration
	@Import(value = {FunctionCaller.class})
	public static class Config{
	}

}
