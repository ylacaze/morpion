package morpion;


public class Game {
	
	private Plateau p;
	
	private Player p1;
	
	private Computer p2 ;
	
	
	
	public void initGame(int tailleX, int tailleY) {
		
		
		p = new Plateau(tailleX,tailleY);
		
		p.initBoard();
		
		p1 = new Player();
		p2 = new Computer();
		
	}
	
	public void play() {
		
		
		int end = 0;
		while (true) {
			Case c = p1.play(p);
			c.setStatut(p1.getSymbol().getVal());
			
			for(Quintuplet q : c.getlQuintu()) {
				if(q.getValue() == Quintuplet.PLAYER_WIN) {
					end = 1;
				}
			}
			if(draw(p)) {
				end = 3;
			}
			if(end != 0) {
				break;
			}
			
			c = p2.play(p);
			c.setStatut(p2.getSymbol().getVal());
			
			for(Quintuplet q : c.getlQuintu()) {
				if(q.getValue() == Quintuplet.AI_WIN) {
					end = 2;
				}
			}
			if(draw(p)) {
				end = 3;
			}
			if(end != 0) {
				break;
			}
			
		}
		if(end == 3) {
			System.out.println("Fin de partie, égalité");
		}
		else {
			System.out.println("Fin de partie, Félicitation au joueur" + end);
		}
		
	}
	
	private boolean draw(Plateau p) {
		boolean equals = true;
		for(Quintuplet q : p.getQuintupletTT()) {
			if(q.isOpen()) {
				equals = false;
				break;
			}
		}
		
		return equals;
	}
	

}
