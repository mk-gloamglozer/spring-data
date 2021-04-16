package com.mkdevs;

interface UserIO{
	Integer getIntegerInput();
	boolean isYesInput();
	String getStringInput();
	void writeln(String text);
	void write(String text);
}