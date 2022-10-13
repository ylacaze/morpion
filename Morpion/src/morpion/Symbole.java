package morpion;

import java.util.ArrayList;
import java.util.List;

public enum Symbole {
	VIDE(0,' '),CERCLE(1,'O'),CROIX(2,'X');

	static private List<Symbole> liste = new ArrayList<Symbole>();
	
	static private boolean remplis = false;
	
	private int val;
	private char charac;
	
	private Symbole(int nb, char c) {
		val = nb;
		charac = c;
		
	}
	
	private static void Remplissage() {
		if(!remplis) {
			liste.add(VIDE);
			liste.add(CERCLE);
			liste.add(CROIX);
		}
	}
	
	public static Symbole getForme(int nb) {
		Remplissage();
		for(Symbole s : liste) {
			if(s.val == nb) {
				return s;
			}
		}
		return null;
		
	}
	
	public char getChar() {
		Remplissage();
		return charac;
	}
	
}
