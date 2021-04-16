package com.mkdevs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkdevs.domain.AbstractDice;

@Repository
interface AbstractDiceRepository extends JpaRepository<AbstractDice, Long>{
	
	Optional<AbstractDice> findByName(String name);
	
	void deleteByName(String name);
	
	boolean existsByName(String name);
}
