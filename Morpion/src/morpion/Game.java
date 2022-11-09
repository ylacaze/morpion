package morpion;

public class Game {
	
	private Plateau p;
	
	private Player p1;
	//TODO ordinateur
	
	
	
	
	public void initGame(int tailleX, int tailleY) {
		p = new Plateau(tailleX,tailleY);
		
		p.initBoard();
		
		p1 = new Player(1);
		//TODO creer ordinateur
	}
	
	public void play() {
		int end = 0;
		while (true) {
			Case c = p1.play(p);
			c.setStatut(p1.getSymbol().getVal());
			
			for(Quintuplet q : c.getlQuintu()) {
				if(q.getValue() == Quintuplet.QUINTUPLET_PLAYER * 5) {
					end = 1;
				}
			}
			if(end != 0) {
				break;
			}
			
			//TODO pareil pour ordi 2 pour fin de partie
			
			
			
			
		}
		
		System.out.println("Fin de partie, Félicitation au joueur" + end);
	}
	
	

}
