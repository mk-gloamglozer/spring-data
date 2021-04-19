package com.mkdevs.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UserIOStdIOTest {
	
	@Mock PrintStream out;
	@Mock ScannerWrapper in;
	
	@InjectMocks UserIOStdIO userio;
	
	@Captor
	ArgumentCaptor<String> anyString;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public void testGetIntegerInput() throws Exception {
		when(in.nextLine()).thenReturn(stringOfNumber(inputNumber()));
		assertThat(userio.getIntegerInput(prompt())).isEqualTo(inputNumber());
		verify(out).print(prompt()+suffix());
	}
	
	@Test
	public void whenNotIntInput_thenAskAgain() {
		// return string first then a number to allow method to exit
		when(in.nextLine()).thenReturn(stringInput(), stringOfNumber(inputNumber()));
		assertThat(userio.getIntegerInput(prompt())).isEqualTo(inputNumber());
		verify(out, atLeastOnce()).println(anyString.capture());
		verify(in, times(2)).nextLine();
		
		// assert that an error message line is printed
		assertThat(anyString.getAllValues()).anyMatch(nonEmptyString());
	}


	@Test
	public void testGetStringInput() throws Exception {
		when(in.nextLine()).thenReturn(stringInput());
		assertThat(userio.getStringInput(prompt())).isEqualTo(stringInput());
		verify(out).print(prompt()+suffix());
	}

	@Test
	public void testIsYesInput() throws Exception {
		when(in.nextLine()).thenReturn("y");
		assertThat(userio.isYesInput(prompt())).isTrue();
		when(in.nextLine()).thenReturn("n");
		assertThat(userio.isYesInput(prompt())).isFalse();
		
		verify(out, times(2)).print(prompt() + yesNoSuffix() + suffix());
	}
	
	@Test
	public void whenIsYesInputUndefined_thenAskAgain() {
		when(in.nextLine()).thenReturn("undefined","y");
		assertThat(userio.isYesInput(prompt())).isTrue();
		verify(out ,atLeastOnce()).println(anyString.capture());
		
		assertThat(anyString.getAllValues()).anyMatch(nonEmptyString());
		
	}

	@Test
	public void testWriteln() throws Exception {
		userio.writeln(stringInput());
		verify(out).println(stringInput());
	}

	@Test
	public void testWrite() throws Exception {
		userio.write(stringInput());
		verify(out).print(stringInput());
	}

	private String yesNoSuffix() {
		return UserIOStdIO.YESNO_SUFFIX;
	}

	private String stringInput() {
		return "input";
	}

	private String suffix() {
		return UserIOStdIO.PROMPT_SUFFIX;
	}

	private String stringOfNumber(int i) {
		return String.valueOf(i);
	}

	private int inputNumber() {
		return 5;
	}

	private String prompt() {
		return "prompt";
	}
	
	private Predicate<? super String> nonEmptyString() {
		return (string) -> !string.isEmpty();
	}
	


}
