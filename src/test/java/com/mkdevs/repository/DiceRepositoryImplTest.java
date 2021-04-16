package com.mkdevs.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkdevs.domain.Dice;
import com.mkdevs.repository.DiceRepository;
import com.mkdevs.repository.DiceRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class DiceRepositoryImplTest {
	
	@Mock Dice dice;
	DiceRepository repo;
	
	@BeforeEach
	public void setup() {
		repo = new DiceRepositoryImpl();
	}

	@Test
	public void testDiceCanBeAdded() {
		Optional<Dice> saveDice = addDiceToRepo();
		assertThat(saveDice).isNotEmpty();
		assertThat(saveDice.get()).isEqualTo(dice);
	}

	@Test
	public void testSavedDiceInFindAll() {
		Dice savedDice = addDiceToRepo().get();
		assertThat(repo.findAll()).contains(savedDice);
	}
	
	@Test
	public void testSavedDiceFoundByName() {
		addDiceToRepo();
		assertThat(repo.findByName(dice.name()))
			.isNotEmpty()
			.contains(dice);
	}
	
	@Test
	public void testFindByNameEmptyIfNoDice() {
		assertThat(repo.findByName(dice.name())).isEmpty();
	}
	
	@Test
	public void testDiceCanBeRemoved() {
		addDiceToRepo();
		assertThat(repo.removeDice(dice))
			.contains(dice);
		assertThat(repo.findAll()).isEmpty();
	}
	
	@Test
	public void whenRepoDoesNotContainDice_thenRemoveReturnsEmpty() {
		assertThat(repo.removeDice(dice)).isEmpty();
	}
	
	
	private Optional<Dice> addDiceToRepo() {
		when(dice.name()).thenReturn("name");
		Optional<Dice> saveDice = repo.saveDice(dice);
		return saveDice;
	}
}
