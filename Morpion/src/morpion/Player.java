package morpion;

import java.util.Scanner;

public class Player {
	private int number; 
	private Symbol symbol; 
	private int score; 
	
	Player(int number){
		this.number = number;
		this.symbol = Symbol.getForm(number);
		this.score = 0; 
	}
	
	public void add_score(Player p) {
		p.score++;
	}

	public int getScore() {
		return score;
	}

	private void affiche_plateau(Plateau p) {
		int x = 0 ;
		for(Case c : p.cases) {
			
			if(c.getPosX() > x) { // Methode a voir après classe case 
				System.out.println("\n");
				x = c.getPosX();
			}
			System.out.println("[" + Symbol.getForm(c.getStatut()).getChar() + "]"); // A changer pour mettre vrai méthode
		}
	}
	
	
	public Case play(Plateau p) {
		
		int posx;
		int posy;
		
		affiche_plateau(p);
		System.out.println("choisir une coordonnée x pour jouer");
		
		Scanner scanIn = new Scanner(System.in);
	    posx = scanIn.nextInt();
	    scanIn.close();            
	    System.out.println(posx);
	    
	    System.out.println("choisir une coordonnée y pour jouer");
	    
	    Scanner scanIn = new Scanner(System.in);
	    posy = scanIn.nextInt();
	    scanIn.close();            
	    System.out.println(posy);
	    
	    for(Case c : p.cases)
	    {
	    	if(c.getPosX() == posx && c.getPosY() == posy)
	    	{
	    		return c;
	    	}
	    }
	    System.err.println("ta case n'est pas dans le plateau paye nous sinon ton ordinnateur se désactiver sous 4 jours");
	    return play(p);    
	}
}
