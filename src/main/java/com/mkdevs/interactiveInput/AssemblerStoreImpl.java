package com.mkdevs.interactiveInput;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssemblerStoreImpl implements AssemblerStore {
	
	@Autowired
	private List<DiceAssembler> assemblers;
	
	@Override
	public List<DiceAssembler> allAssemblers() {
		return assemblers;
	}

}
