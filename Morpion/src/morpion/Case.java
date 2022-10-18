package morpion;

import java.util.ArrayList;
import java.util.List;

public class Case {
	
	private int posx ;
	private int posy ;
	private int value ;
	private int statut;
	private List<Quintuplet> lQuintu = new ArrayList<Quintuplet>();
	
	Case(int x, int y, int val, Symbol s){
		this.posx = x;
		this.posy = y;
		this.value = val;
		this.statut = 0;
		
	}
	
	private void setVal() {
		value = 0;
		for(Quintuplet c : lQuintu) {
			value += c.getValue();
		}
	}
	
	public List<Quintuplet> qListe(){		
		return this.lQuintu;
	}
	
	public int getStatut() {
		return this.statut;
	}
	
	public void setStatut(int stat) {
		this.statut = stat;
		for(Quintuplet c : lQuintu) {
			 c.setNewVal();
		}
		setVal();
	}
	
	public void setList(List<Quintuplet> qList) {
		lQuintu = qList;
	}
	
}
