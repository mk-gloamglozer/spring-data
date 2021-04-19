package com.mkdevs.io;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ScannerWrapper {
	@Autowired
	private Scanner scanner;
	
	public String nextLine() {
		return scanner.nextLine();
	}
	
}
