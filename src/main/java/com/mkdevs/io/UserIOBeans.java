package com.mkdevs.io;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserIOBeans {
	
	@Bean
	public Scanner stdInScanner() {
		return new Scanner(System.in);
	}
	
	@Bean
	public PrintStream stdOutStream() {
		return System.out;
	}

}
