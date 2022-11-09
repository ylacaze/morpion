package morpion;

import java.util.ArrayList;
import java.util.List;

public enum Symbol {
	EMPTY(0,' '),CIRCLE(1,'O'),CROSS(2,'X');

	static private List<Symbol> liste = new ArrayList<Symbol>();
	
	static private boolean filled = false;
	
	private int val;
	private char charac;
	
	private Symbol(int nb, char c) {
		val = nb;
		charac = c;
		
	}
	
	public int getVal() {
		return this.val;
	}
	
	private static void Fill() {
		if(!filled) {
			liste.add(EMPTY);
			liste.add(CIRCLE);
			liste.add(CROSS);
		}
	}
	
	public static Symbol getForm(int nb) {
		Fill();
		for(Symbol s : liste) {
			if(s.val == nb) {
				return s;
			}
		}
		return null;
		
	}
	
	public char getChar() {
		Fill();
		return charac;
	}
	
}
