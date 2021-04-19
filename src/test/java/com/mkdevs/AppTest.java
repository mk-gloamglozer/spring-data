package com.mkdevs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;

import com.mkdevs.repository.DiceRepository;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest 
{
	@MockBean CommandLineRunner runner;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private DiceRepository dicerepo;

    @Test
    public void testContextLoads()
    {
        assertThat(true).isTrue();
    }
    
    @Test
    public void testProfileIsJpa() {
    	assertThat(env.getActiveProfiles()).contains("jpa");
    }
    
    @Test
    public void testDiceRepoPopulated() {
    	assertThat(dicerepo.findAll()).isNotEmpty();
    }
}
