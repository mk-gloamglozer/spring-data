package com.mkdevs;

import java.util.List;

import com.mkdevs.FunctionCaller.FunctionOption;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
public class App 
{
    public static void main( String[] args )
    {
    	FunctionCaller caller = AppConstructor.init();

    	DiceFactory factory = AppConstructor.diceFactory();
    	AppConstructor.initialiseRepoWithDice(List.of(
    			factory.createDice("D6", "D6"),
    			factory.createDice("FixedD20", "D20-fixed"),
    			factory.createDice("D20", "D20")
    			));
    	
    	caller.addFunction(new FunctionOption() {
			
			@Override
			public String id() {
				return "Exit";
			}
			
			@Override
			public void doIt() {
				System.exit(0);
			}
		});
    	
    	while(true) {
    		try {
    			caller.askAndCall();
    		} catch (Exception e) {
    			log.error("the program encountered a problem and has to close", e);
			}
    	}
    }
}
