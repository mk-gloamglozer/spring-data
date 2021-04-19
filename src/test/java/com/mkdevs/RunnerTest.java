package com.mkdevs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.mkdevs.io.UserIO;
import com.mkdevs.utils.FunctionCaller;
import com.mkdevs.utils.FunctionCaller.FunctionOption;

@ExtendWith(MockitoExtension.class)
public class RunnerTest {
	
	@Mock FunctionCaller caller;
	@Mock UserIO userio;
	
	Runner runner;

	@Captor
	ArgumentCaptor<FunctionOption> function;
	
	@BeforeEach
	protected void setUp() throws Exception {
		runner = spy(new Runner(caller,userio,true));
	}

	@Test
	public void testAddExitFunction() throws Exception {
		runner.addExitFunction();
		verify(caller).addFunction(function.capture());
		assertThat(function.getValue().id()).isEqualTo("Exit");
	}
	
	@Test
	public void testExitFunction_setsIsRunningToFalse() {
		runner.addExitFunction();
		verify(caller).addFunction(function.capture());
		function.getValue().doIt();
		verify(runner).setRunning(false);
	}

	@Test
	public void testRun() throws Exception {
		doAnswer(breakLoop()).when(caller).askAndCall();
		runner.run();
		verify(caller).askAndCall();
	}
	

	@Test
	public void whenRunEncountersError_thenMessagePrinted_andUserAskedForInput() throws Exception {
		doAnswer(throwOnceThenBreak()).when(caller).askAndCall();
		runner.run();
		verify(caller, times(2)).askAndCall();
		verify(userio).getStringInput("[Enter]");

	}

	private Answer throwOnceThenBreak() {
		return new Answer<Object>() {
			private int count = 0;
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				if(count!=0) {
					runner.setRunning(false);
					return null;
				} else {
					count ++;
					throw new RuntimeException("Don't worry just thrown as part of a test");
				}
			}
		};
	}

	private Answer breakLoop() {
		return (invocation) -> {
			runner.setRunning(false);
			return null;};
	}

}
