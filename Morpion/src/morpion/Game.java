package morpion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	private Plateau p;
	
	private Player p1;
	
	private Computer p2 ;
	
	private Player p3;
	
	private List<Player> players = new ArrayList<Player>();
	
	
	
	public void initGame(int tailleX, int tailleY) {
		
		
		p = new Plateau(tailleX,tailleY);
		
		p.initBoard();
		
		new Symbol(0,' ');
		
		System.out.println("nb Joueur total :");
		Scanner scanIn = new Scanner(System.in);
	    int nbJ = scanIn.nextInt();
	    
	    System.out.println("nb Joueur Humain :");
	    int nbPlayer = scanIn.nextInt();
	    
	    int nbOrdi = nbJ-nbPlayer;
	    
	    int nbPlayerCreat = 0;
	    int nbCPUCreat = 0;
	    
	    int nbCourant = 1;
	    
	    while(true) {
	    	if(nbPlayerCreat < nbPlayer) {
	    		players.add(newPlayer(nbCourant));
	    		nbCourant++;
	    		nbPlayerCreat++;
	    	}
	    	if(nbCPUCreat < nbOrdi) {
	    		players.add(newCPU(nbCourant));
	    		nbCourant++;
	    		nbCPUCreat++;
	    	}
	    	if(nbPlayerCreat >= nbPlayer && nbCPUCreat >= nbOrdi) {
	    		break;
	    	}
	    }
	}
	
	public void play() {
		
		
		int end = 0;
		while (true) {
			for(Player pCourant : players) {
				Case c = pCourant.play(p);
				c.setStatut(pCourant.getSymbol().getVal());
				
				end = End(p,c);
				if(end != 0) {
					break;
				}
			}
			if(end != 0) {
				break;
			}
		}
		Player.affiche_plateau(p);
		if(end == -1) {
			System.out.println("\nFin de partie, égalité");
			
		}
		else {
			System.out.println("\nFin de partie, Félicitation au joueur" + end);
		}
		
	}
	
	private int End(Plateau p, Case c) {
		for(Quintuplet q : p.getQuintupletTT()) {
			if(q.getValue() == Quintuplet.AI_WIN) {
				System.out.println("\nFini");
				return q.getCases().get(0).getStatut();
			}else {
				if(q.getValue() == Quintuplet.PLAYER_WIN) {
					System.out.println("\nFini");
					return q.getCases().get(0).getStatut();
				}
			}
		}
		if(draw(p)) {
			System.out.println("\nFini");
			return -1;
		}
		return 0;
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
	
	private Player newPlayer(int num) {
		System.out.println("\nJoueur " + num +"choisir votre symbole");
		
		
		char sym;
		while(true) {
			Scanner scanIn = new Scanner(System.in);
		    String s = scanIn.next();
		    
		    if(s.length() == 1) {
		    	sym = s.charAt(0);
		    	break;
		    }
		}
		
		return new Player(new Symbol(num,sym));
	}
	
	private Computer newCPU(int num) {
		
		char sym;
		while(true) {
			System.out.println("\n veuillez choisir un symbole pour l'ordi " + num);
			Scanner scanIn = new Scanner(System.in);
		    String s = scanIn.next();
		    
		    if(s.length() == 1) {
		    	sym = s.charAt(0);
		    	break;
		    }
		}
		
		return new Computer(new Symbol(num,sym));
	}

}
