package morpion;

import java.util.ArrayList;
import java.util.List;

public class Quintuplet {

	
	public static final int valCircle = 5;
	public static final int valCross = 6;
	
	private int valeur = 0;
	private List<Case> cases = new ArrayList<Case>();
	private boolean open = true;
	
	public Quintuplet(Case c1, Case c2,Case c3,Case c4,Case c5) {
		cases.add(c1);
		cases.add(c2);
		cases.add(c3);
		cases.add(c4);
		cases.add(c5);
	}
	
	public void setNewVal() {
		int nbS = 0;
		int numS;
		for(Case c : cases) {
			if(c.getStatut() != 0) {
				if(nbS == 0) {  //premiére case avec un symbole
					nbS++;
					numS = c.getStatut();
				}
				if(c.getStatut() != numS) { //deux case de deux symbole différent
					open = false;
					valeur = 0;
					break;
				}
				nbS++;  // deux case de meme symbole
				
			}
		}
		if()   //calcule valeur
	}
	
}
