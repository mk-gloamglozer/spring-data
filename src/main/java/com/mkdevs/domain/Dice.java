package com.mkdevs.domain;

import com.mkdevs.IDable;

public interface Dice extends IDable{
	int roll();
	String name();
	
	@Override
	default String id() {
		return name();
	}
}
