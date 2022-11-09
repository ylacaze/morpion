package morpion;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	
	private static int maxX = 0;
	private static int maxY = 0;
	//private static int SIZE = maxX*maxY;
	private List<Quintuplet> quintupletsTT = new ArrayList<Quintuplet>();
	private List<Quintuplet> quintupletsOpen;
	private List<Case> casesPlateau = new ArrayList<Case>();
	private Case plateau[][] = new Case[0][0];
	
	public Plateau(int posx, int posy) {
		maxX = posx-1;
		maxY = posy-1;
		this.plateau = new Case[posx][posy];
	}
	
	
	public Case getCase(int posx , int posy) {
		
		for(int i = 0; i < this.plateau.length; i++) {
			if (i == posx) {
				for(int j = 0; j < this.plateau[i].length; j++) {
					if (j == posy) {
						return plateau[i][j];
					}
				}
			}
		}
		//enlever le return ct juste pour verif la valeur du renvoie et enlever les warning
		return plateau[0][0];
		//renvoie d'une exception pour le fait que les coordonnées ne correspondent à aucune case
	}
	
	public Case getCase2_0(int posx, int posy) {
		
		if(!(this.casesPlateau.isEmpty())) {
			for(Case c : casesPlateau) {
				if(c.getPosX() == posx && c.getPosY() == posy) {
					return c; 
				}
				//sino il continu la boucle jusqu'à trouver notre case
			}
		}
		
		return null;
		//faire une execption ici pour le cas ou on ne trouve pas la case 
	}	
	
	public List<Quintuplet> initBoard(){
		
		//init cases
		for(int i = 0; i<maxY+1;i++) {
			for(int j = 0; j<maxX+1; j++) {
				plateau[j][i] = new Case(j,i,0);
			}
		}
		
		
		//int ii = 0;
		for(int i = 0; i < this.plateau.length; i++ ) {
			
			for(int j = 0; j < plateau[i].length ; j++) {
				Case c = plateau[i][j];
				
				//on verifie les cases par rapport à la droite
				if(c.getPosX() + 4 <= maxX) {
					quintupletsTT.add(new Quintuplet(c, plateau[i+1][j], plateau[i+2][j], plateau[i+3][j], plateau[i+4][j]));
				}
				
				//on vérifie les cases par rapport au bas
				if(c.getPosY() + 4 <= maxY) {
					quintupletsTT.add(new Quintuplet(c, plateau[i][j+1], plateau[i][j+2], plateau[i][j+3], plateau[i][j+4]));
				}
				
				//on vérifie maintenant les diagonales supérieures Bas-Droite 
				if(c.getPosX() + 4 <= maxX && c.getPosY() + 4 <= maxY) {
					quintupletsTT.add(new Quintuplet(c, plateau[i+1][j+1], plateau[i+2][j+2], plateau[i+3][j+3], plateau[i+4][j+4]));
				}
				
				//on vérifie pour finir les diagonales supérieures Haut-Droite 
				if(c.getPosX() + 4 <= maxX && c.getPosY() - 4 >= 0) {
					quintupletsTT.add(new Quintuplet(c, plateau[i+1][j-1], plateau[i+2][j-2], plateau[i+3][j-3], plateau[i+4][j-4]));
				}
			}

		}
		this.quintupletsOpen = quintupletsTT;
		return quintupletsTT;
	}


	public List<Case> getCasesPlateau() {
		return casesPlateau;
	}
	
	public List<Quintuplet> getQuintupletTT(){
		return this.quintupletsTT;
	}
}
