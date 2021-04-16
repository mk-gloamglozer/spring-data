package com.mkdevs.domain;

import com.mkdevs.IDable;

public interface Dice extends IDable{
	int roll();
	int roll(int times);
	String name();
	
	@Override
	default String id() {
		return name();
	}
}
