package com.example.jfxmorpion.morpion;

import java.util.ArrayList;
import java.util.List;

public class Symbol {


	static private List<Symbol> liste = new ArrayList<Symbol>();
	
	static private boolean filled = false;
	
	private int val;
	private char charac;
	
	Symbol(int nb, char c) {
		val = nb;
		charac = c;
		liste.add(this);
	}
	
	public int getVal() {
		return this.val;
	}
	

	
	public static Symbol getForm(int nb) {
		
		for(Symbol s : liste) {
			if(s.val == nb) {
				return s;
			}
		}
		return null;
		
	}
	
	public char getChar() {
		
		return charac;
	}
	
}
