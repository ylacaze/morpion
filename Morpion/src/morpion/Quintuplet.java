package morpion;

import java.util.ArrayList;
import java.util.List;

public class Quintuplet {

	private static final int QUINTUPLET_CLOSED = 0;
	private static final int QUINTUPLET_EMPTY = 1;
	private static final int QUINTUPLET_AI = 6;
	private static final int QUINTUPLET_PLAYER = 5;
	
	private int value = 0;
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
		int numS = 0;
		for(Case c : cases) {
			if(c.getStatut() != 0) {
				if(nbS != 0 && c.getStatut() != numS) { //deux case de deux symbole différent
					open = false;
					value = QUINTUPLET_CLOSED;
					break;
				}
				
				if(nbS == 0) {  //premiére case avec un symbole
					numS = c.getStatut();
				}
				
				nbS++;  // deux case de meme symbole
				
			}
		}
		if(open) {			//calcule valeur
			if(nbS == 0){
				value = QUINTUPLET_EMPTY;
			}
			else {
				if(numS == 2) {
					value = nbS * QUINTUPLET_AI;
				}
				else {
					value = nbS * QUINTUPLET_PLAYER;
				}
			}
				
		}
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int x) {
		this.value = x;
	}
	
}
