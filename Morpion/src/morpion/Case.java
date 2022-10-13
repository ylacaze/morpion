package morpion;

import java.util.ArrayList;
import java.util.List;

public class Case {
	
	private int posx ;
	private int posy ;
	private int value ;
	private Symbol symbol;
	private List<Quintuplet> lQuintu = new ArrayList<Quintuplet>();
	
	Case(int x, int y, int val, Symbol s){
		this.posx = x;
		this.posy = y;
		this.value = val;
		this.symbol = s.EMPTY;
		
	}
	
	public List<Quintuplet> qListe(int x, int y){
		List<Quintuplet> lQ = new ArrayList<Quintuplet>();
		
		
	}
	

}
