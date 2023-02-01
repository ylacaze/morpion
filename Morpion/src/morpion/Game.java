package morpion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	private Plateau p;
	
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
		
		
		boolean end = false;
		while (true) {
			for(Player pCourant : players) {
				Case c = pCourant.play(p);
				c.setStatut(pCourant.getSymbol().getVal());
				
				for(Quintuplet q : c.getlQuintu()) {
					q.setNewVal(-1);
					if(q.getValue() == Quintuplet.PLAYER_WIN) {
						p.changeQ(q);
						break;
					}
				}
				
				end = draw(p);//End(p,c);
				if(end) {
					break;
				}
			}
			if(end) {
				break;
			}
		}
		Player.affiche_plateau(p);
		
		for(Player j : players) {
			j.calculScore(p);
		}
		
		List<Integer> indice = new ArrayList<Integer>();
		int scoreMax = -20;
		boolean equal = false;
		
		int compteur = 1;
		
		for(Player j : players) {
			
			if (j.getScore() == scoreMax) {
				equal = true;
				indice.add(compteur);
			}
			
			if(j.getScore() > scoreMax) {
				scoreMax = j.getScore();
				equal = false;
				indice.clear();
				indice.add(compteur);
			}
			
			compteur++;
		}
		
		if(equal) {
			System.out.println("\nFin de partie, égalité aux Joueurs : " + scoreMax);
			for(Integer i : indice) {
				System.out.println("- "+i);
			}
			
		}
		else {
			System.out.println("\nFin de partie, Félicitation au Joueur" + indice.get(0) + " \nAvec un score de "+scoreMax);
		}
		
	}
	
	private int End(Plateau p, Case c) {
		/*
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
		*/
		
		
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
