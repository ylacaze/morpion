package com.example.jfxmorpion.morpion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

	private String[] args;
	private Plateau p;
	
	private List<Player> players = new ArrayList<Player>();

	private Main application = null;

	public Game(String[] args){
		this.args = args;
	}
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
	
	public void play(Main main) throws InterruptedException {
		
		application = main;
		boolean end = false;
		while (true) {
			int i =1;
			for(Player pCourant : players) {
				application.getNomLabel().setText("Joueur n'" + i);
				application.getSymboleLabel().setText("Symbole : " + pCourant.symbol.getChar());
				Case c = pCourant.play(p,application);
				c.setStatut(pCourant.getSymbol().getVal());
				application.getLabels()[c.getPosX()][c.getPosY()].setText(" "+pCourant.getSymbol().getChar()+" ");
				for(Quintuplet q : c.getlQuintu()) {
					q.setNewVal(-1);
					if(q.getValue() == Quintuplet.PLAYER_WIN) {
						p.changeQ(q);
					}
				}
				
				end = draw(p);
				if(end) {
					break;
				}
				i++;
			}
			if(end) {
				break;
			}
		}
		p.colorPlat();
		
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
	

	public boolean draw(Plateau p) {

		boolean equals = true;
		for(Quintuplet q : p.getQuintupletTT()) {
			q.setNewVal(0);
			if(q.getValue() != Quintuplet.PLAYER_WIN && q.getValue() != Quintuplet.QUINTUPLET_CLOSED && q.getValue() != Quintuplet.AI_WIN) {
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

	public Main getApplication(){
		return application;
	}

	public Plateau getP() {
		return p;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
