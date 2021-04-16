package com.mkdevs.io;

import java.util.Scanner;

public class UserIOStdIO implements UserIO{
	private Scanner input;

	public UserIOStdIO() {
		input = new Scanner(System.in);
	}
	
	@Override
	public Integer getIntegerInput() {
		while(true) {
			String inputString = input.nextLine();
			try {
				return Integer.parseInt(inputString);
			} catch (NumberFormatException e) {
				System.out.println("The input must be an integer");
			}
		}
	}

	@Override
	public String getStringInput() {
		return input.nextLine();
	}
	

	@Override
	public boolean isYesInput() {
		while(true) {
			String inputString = input.nextLine();
			switch(inputString){
				case "y":
				case "Y":
					return true;
				case "n":
				case "N":
					return false;
				default:
					System.out.println("The response must be y/n");
			}
		}
	}

	@Override
	public void writeln(String text) {
		System.out.println(text);
		
	}

	@Override
	public void write(String text) {
		System.out.print(text);
	}

	
	@Override
	protected void finalize() throws Throwable {
		input.close();
	}
	
}