package com.mkdevs.io;

public interface UserIO{
	Integer getIntegerInput(String prompt);
	boolean isYesInput(String prompt);

	String getStringInput(String prompt);

	void writeln(String text);
	void write(String text);
}