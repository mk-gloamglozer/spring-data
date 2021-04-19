package com.mkdevs.io;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserIOStdIO implements UserIO{
	static final String PROMPT_SUFFIX = ": ";
	static final String YESNO_SUFFIX = " (y/n)";
	
	@Autowired
	private ScannerWrapper input;
	
	@Autowired 
	private PrintStream output;
	

	@Override
	public boolean isYesInput(String prompt) {
		this.write(prompt + yesNoSuffix() + promptEnd());
		while(true) {
			String inputString = input.nextLine();
			newline();
			switch(inputString){
				case "y":
				case "Y":
					return true;
				case "n":
				case "N":
					return false;
				default:
					this.writeln("The response must be y/n");
			}
		}
	}


	@Override
	public Integer getIntegerInput(String prompt) {
		while(true) {
			write(prompt + promptEnd());
			String inputString = input.nextLine();
			newline();
			try {
				return Integer.parseInt(inputString);
			} catch (NumberFormatException e) {
				writeln("The input must be an integer");
			}
		}
	}

	
	@Override
	public String getStringInput(String prompt) {
		write(prompt + promptEnd());
		String inString = input.nextLine();
		newline();
		return inString;
	}

	@Override
	public void writeln(String text) {
		output.println(text);
		output.flush();
	}
	

	@Override
	public void write(String text) {
		output.print(text);
		output.flush();
	}

	
	private void newline() {
		writeln("");
	}
	
	private String promptEnd() {
		return PROMPT_SUFFIX;
	}

	private String yesNoSuffix() {
		return YESNO_SUFFIX;
	}

	
}