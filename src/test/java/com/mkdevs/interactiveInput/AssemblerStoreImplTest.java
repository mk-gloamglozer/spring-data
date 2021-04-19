package com.mkdevs.interactiveInput;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mkdevs.interactiveInput.AssemblerStore;
import com.mkdevs.interactiveInput.AssemblerStoreImpl;
import com.mkdevs.interactiveInput.DiceAssembler;

@ExtendWith(SpringExtension.class)
@SpringBootTest (classes = AssemblerStoreImplTest.Config.class)
@Import(AssemblerStoreImpl.class)
public class AssemblerStoreImplTest {

	@MockBean(name = "a1") DiceAssembler assembler;
	@MockBean(name = "a2") DiceAssembler anotherAssembler;
	
	@Autowired AssemblerStore assemblerStore;


	@Test
	public void testAllAssemblers() throws Exception {
		assertThat(assemblerStore.allAssemblers()).contains(assembler, anotherAssembler);
	}

	@Configuration
	public static class Config{
		
	}

}
