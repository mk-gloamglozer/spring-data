package com.mkdevs.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.AbstractDice;
import com.mkdevs.domain.Dice;

@ExtendWith(MockitoExtension.class)
public class DiceRepositoryJPABackedTest {
	
	@Mock AbstractDiceRepository internalRepo;
	
	@Mock AbstractDice abstractDice;
	
	@Mock Dice dice;
	
	@InjectMocks DiceRepositoryJPABacked repo;

	@Test
	public void testFindAll() throws Exception {
		when(internalRepo.findAll()).thenReturn(List.of(abstractDice));
		assertThat(repo.findAll()).containsExactly(abstractDice);
	}

	@Test
	public void testFindByName() throws Exception {
		when(internalRepo.findByName(diceName())).thenReturn(Optional.of(abstractDice));
		assertThat(repo.findByName(diceName())).contains(abstractDice);

		when(internalRepo.findByName(diceName())).thenReturn(Optional.empty());
		assertThat(repo.findByName(diceName())).isEmpty();
	}


	@Test
	public void testSaveDice() throws Exception {
		when(internalRepo.save(abstractDice)).thenReturn(abstractDice);
		assertThat(repo.saveDice(abstractDice)).contains(abstractDice);
		
		when(internalRepo.save(abstractDice)).thenThrow(HibernateException.class);
		assertThrows(DiceStorageException.class, doSaveAbstractDice());
		
		assertThrows(DiceStorageException.class, doSaveAnyDice());
	}
	
	private Executable doSaveAnyDice() {
		return () -> repo.saveDice(dice);
	}

	private Executable doSaveAbstractDice() {
		return () -> repo.saveDice(abstractDice);
	}


	@Test
	public void testRemoveDice() throws Exception {

		when(internalRepo.existsByName(diceName())).thenReturn(true);
		when(abstractDice.name()).thenReturn(diceName());
		assertThat(repo.removeDice(abstractDice)).contains(abstractDice);

		when(internalRepo.existsByName(diceName())).thenReturn(false);
		assertThat(repo.removeDice(abstractDice)).isEmpty();
	}

	private String diceName() {
		return "name";
	}
}
