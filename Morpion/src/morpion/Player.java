package morpion;

import java.util.Scanner;

public class Player {
	private int number; 
	private Symbol symbol; 
	private int score; 
	
	public Player(int number){
		this.number = number;
		this.symbol = Symbol.getForm(number);
		this.score = 0; 
	}
	
	public Symbol getSymbol() {
		return this.symbol;
	}
	
	public void add_score(Player p) {
		p.score++;
	}

	public int getScore() {
		return score;
	}

	private void affiche_plateau(Plateau p) {
		int x = 0 ;
				
		for(Case c : p.getCasesPlateau()) {

			if(c.getPosX() == x) { // Methode a voir après classe case 
				System.out.print("\n");
			}
			System.out.print("[" + Symbol.getForm(c.getStatut()).getChar() + "]"); // A changer pour mettre vrai méthode
		}
		
	}
	
	
	public Case play(Plateau p) {
		
		int posx;
		int posy;
		
		affiche_plateau(p);
		System.out.println("\nchoisir une coordonnée x pour jouer");
		
		Scanner scanIn = new Scanner(System.in);
	    posx = scanIn.nextInt();
	                
	    System.out.println(posx);
	    	    
	    System.out.println("choisir une coordonnée y pour jouer");

		Scanner scanIn1 = new Scanner(System.in);

	    posy = scanIn1.nextInt();
	    //scanIn1.close(); 
	    //scanIn.close();
	    System.out.println(posy);
	    
	   
	    for(Case c : p.getCasesPlateau())
	    {
	    	if(c.getPosX() == posx && c.getPosY() == posy)
	    	{
	    		if(c.getStatut() != 0 ) {
	    			 System.err.println("cette case est déjà joué");
	    			 return play(p);
	    		}else return c;
	    	}
	    }
	    System.err.println("ta case n'est pas dans le plateau paye nous sinon ton ordinnateur se désactiver sous 4 jours");
	    return play(p);    
	}
}
