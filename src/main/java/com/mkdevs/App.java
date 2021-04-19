package com.mkdevs;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mkdevs.interactiveInput.FunctionCaller;
import com.mkdevs.interactiveInput.FunctionCaller.FunctionOption;
import com.mkdevs.repository.DiceRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
@SpringBootApplication
public class App  {

    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args).close();
    }
    
}
