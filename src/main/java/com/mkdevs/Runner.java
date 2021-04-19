package com.mkdevs;

import static lombok.AccessLevel.PACKAGE;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mkdevs.domain.BasicDiceFactory.BasicTemplate;
import com.mkdevs.domain.DiceFactory;
import com.mkdevs.domain.FixedDiceFactory.FixedDiceTemplate;
import com.mkdevs.io.UserIO;
import com.mkdevs.repository.DiceRepository;
import com.mkdevs.utils.FunctionCaller;
import com.mkdevs.utils.FunctionCaller.FunctionOption;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Runner implements CommandLineRunner {

	
	@Autowired FunctionCaller caller;
	@Autowired UserIO userio;

	@Setter(PACKAGE)
	private boolean isRunning = true;
    
    @PostConstruct
    public void addExitFunction() {

    	caller.addFunction(new FunctionOption() {
			
			@Override
			public String id() {
				return "Exit";
			}
			
			@Override
			public void doIt() {
				setRunning(false);
			}
		});
    }

	@Override
	public void run(String... args) throws Exception {

    	while(isRunning) {
    		try {
    			caller.askAndCall();
    		} catch (Exception e) {
    			log.error("something bad happened", e);
    			userio.writeln("Woops, something went wrong.");
    			userio.getStringInput("[Enter]");
			}
    	}
	}

	
	
	
	

}
