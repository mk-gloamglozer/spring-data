package com.mkdevs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mkdevs.utils.DiceCalcUtil;

@Configuration
public class BeanConfig {
	
	@Bean
	public DiceCalcUtil diceCalcUtil() {
		return new DiceCalcUtil() { };
	}

}
