package com.mkdevs;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.mkdevs.domain.BasicDiceFactory.BasicTemplate;
import com.mkdevs.domain.DiceFactory;
import com.mkdevs.domain.FixedDiceFactory.FixedDiceTemplate;
import com.mkdevs.repository.DiceRepository;

@Configuration
class InitialPopulator {

	@Autowired DiceFactory<BasicTemplate> basicFactory;
	@Autowired DiceFactory<FixedDiceTemplate> fixedFactory;
	@Autowired DiceRepository repo;

    @PostConstruct
    public void initRepo() {
    	// load a couple of basic dice
    	List.of(basicTemplate("D6", 6),
    			basicTemplate("D20", 20))
    		.stream()
    		.map(basicFactory::build)
    		.forEach(repo::saveDice);
    	// and a fixed dice
    	List.of(fixedTemplate("Fixed D6 (always 6)", 6))
    		.stream()
    		.map(fixedFactory::build)
    		.forEach(repo::saveDice);
    }
    
	public BasicTemplate basicTemplate(String name, int sides) {
		return new BasicTemplate() {
			
			@Override
			public int numberOfSides() {
				return sides;
			}
			
			@Override
			public String name() {
				return name;
			}
		};
	}
	
	public FixedDiceTemplate fixedTemplate(String name, int fixedNumber) {
		return new FixedDiceTemplate() {
			
			@Override
			public String name() {
				return name;
			}
			
			@Override
			public int fix() {
				return fixedNumber;
			}
		};
	}
}
