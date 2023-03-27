package com.example.jfxmorpion.morpion;

import java.util.Scanner;

public class Player {
	protected Symbol symbol; 
	private int score; 
	
	public Player(Symbol s){
		this.symbol = s;
		this.score = 0; 
	}
	
	public Symbol getSymbol() {
		return this.symbol;
	}
	
	public void add_score() {
		this.score++;
	}

	public int getScore() {
		return score;
	}

	public static void affiche_plateau(Plateau p) {
		int x = 0 ;
		int j = 0;
		System.out.print("  ");
		for(int i =0; i<= p.getX();i++) {
			if(i >= 10) {
				System.out.print(" "+ i); 
			}else {
				System.out.print(" "+ i + " "); 
			}		
		}
		
		for(Case c : p.getCasesPlateau()) {

			if(c.getPosX() == x) {
				System.out.print("\n");
				System.out.print(j);
				if(j<10) {
					System.out.print(" ");
				}
				
				j++;
			}
			System.out.print("[" + Symbol.getForm(c.getStatut()).getChar() + "]"); 
		}
		
	}
	
	
	public Case play(Plateau p) {
		
		int posx;
		int posy;	
		
		affiche_plateau(p);
		System.out.println("\nJoueur " + this.symbol.getVal() +"choisir une coordonnée x pour jouer");
		
		Scanner scanIn = new Scanner(System.in);
	    posx = scanIn.nextInt();
	                
	    System.out.println(posx);
	    	    
	    System.out.println("Joueur " + this.symbol.getVal() +"choisir une coordonnée y pour jouer");

		Scanner scanIn1 = new Scanner(System.in);

	    posy = scanIn1.nextInt();
	    
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
	    System.err.println("Veuillez jouer dans le tableau");
	    return play(p);    
	}
	
	public void calculScore(Plateau p) {
		for(Quintuplet q : p.getQuintupletTT()) {
			q.setNewVal(-1);
			if(q.getValue() == Quintuplet.PLAYER_WIN) {
				if (q.getCases().get(0).getStatut() == this.symbol.getVal()) {
					score++;
				}
			}
		}
	}
}
