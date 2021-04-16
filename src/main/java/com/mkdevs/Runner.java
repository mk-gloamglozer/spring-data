package com.mkdevs;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mkdevs.domain.DiceFactory;
import com.mkdevs.repository.DiceRepository;
import com.mkdevs.utils.FunctionCaller;
import com.mkdevs.utils.FunctionCaller.FunctionOption;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

	
	@Autowired DiceFactory factory;
	@Autowired DiceRepository repo;
	@Autowired FunctionCaller caller;

    @PostConstruct
    public void initRepo() {
    	List.of(
    			factory.createDice("D6", "D6"),
    			factory.createDice("FixedD20", "D20-fixed"),
    			factory.createDice("D20", "D20"))
			.forEach(repo::saveDice);
    }
    
    @PostConstruct
    public void addExitFunction() {

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
    }

	@Override
	public void run(String... args) throws Exception {

    	while(true) {
    		try {
    			caller.askAndCall();
    		} catch (Exception e) {
    			log.error("the program encountered a problem and has to close", e);
			}
    	}
	}

}
