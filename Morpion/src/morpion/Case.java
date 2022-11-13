package morpion;

import java.util.ArrayList;
import java.util.List;

public class Case {
	
	private int posx ;
	private int posy ;
	private int value ;
	private int statut;
	private List<Quintuplet> lQuintu = new ArrayList<Quintuplet>();
	
	public Case(int x, int y, int val){
		this.posx = x;
		this.posy = y;
		this.value = val;
		this.statut = 0;
		
	}
	
	public void setVal() {
		value = 0;
		for(Quintuplet c : lQuintu) {
			if(statut ==0) {
			value += c.getValue();
			}
			else c.setValue(-1);
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
		for(Quintuplet q : lQuintu) {
			 q.setNewVal();
		}
	}
	
	
	public void setList(List<Quintuplet> qList) {
		lQuintu = qList;
	}
	
	public int getPosX() {
		return posx;
	}
	
	public int getPosY() {
		return posy;
	}
	
	public int getValue() {
		return value;
	}

	public List<Quintuplet> getlQuintu() {
		return lQuintu;
	}
	
	public void addQuint(Quintuplet q) {
		lQuintu.add(q);
	}
	
	
}
