package com.example.jfxmorpion.morpion;

import java.util.List;
import java.util.Scanner;

public class Computer extends Player {
	 
	public Computer(Symbol s){
		super(s);
	}
	
	@Override
	public Case play(Plateau p) {
		
		List<Quintuplet> lQuintu = p.getQuintupletTT();
		for(Quintuplet q : lQuintu) {
			 q.setNewVal(this.symbol.getVal());
		}
		
		int highest_value = 0;
		Case highest_case = p.getCase(0, 0);
		for(Case c : p.getCasesPlateau()) {
			c.setVal();
			if ( c.getValue() > highest_value ) {
				highest_value = c.getValue();
				highest_case = c;
			}
		}
		return highest_case;
	}
	
	
}
