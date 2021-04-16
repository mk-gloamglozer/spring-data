package com.mkdevs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class AbstractDice implements Dice {
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(unique = true,
			name = "NAME")
	private String name;
	
	public AbstractDice(String name) {
		this.name = name;
	}
	
	@Override
	public String name() {
		return name;
	}
	
}
