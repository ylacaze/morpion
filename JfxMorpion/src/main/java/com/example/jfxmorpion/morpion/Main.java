package com.example.jfxmorpion.morpion;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Scanner;

public class Main extends Application{
	private static Game g;
	private boolean ready = false;
	private VBox boxPrincipale;
	private GridPane grille;

	private Label[][] labels;
	private Label nomLabel,symboleLabel;

	private int coordX,coordY;
	private boolean jouer = false;

	private Main m;

	private List<Player> players;
	private int playerCourant = 1;



	private static int x,y;

	public static void main(String[] args){
		Main m = new Main();
		// TODO Auto-generated method stub
		System.out.println("Choix de la taille du tableau");

		System.out.println("Choix des X (lignes)");
		
		Scanner size_x = new Scanner(System.in);
		int tailleX = size_x.nextInt();
		
		System.out.println("Choix des Y (colonnes)");
		
		Scanner size_y = new Scanner(System.in);
		int tailleY = size_y.nextInt();



		g = new Game(args);
		x=tailleX;
		y=tailleY;
		g.initGame(tailleX, tailleY);
		launch(args);
		size_x.close();
		size_y.close();
		

	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		players = g.getPlayers();
		grille = new GridPane();
		grille.setGridLinesVisible(true);
		labels = new Label[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Label l = new Label("   ");
				l.setPrefHeight(25); l.setPrefWidth(25);
				labels[i][j] = l;
				grille.add(l,i,j);
				int finalI = i;
				int finalJ = j;
				l.setOnMouseClicked(e -> eventLabel(finalI, finalJ));
			}
		}
		grille.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
				new CornerRadii(2),new BorderWidths(2))));
		boxPrincipale = new VBox();

		nomLabel = new Label("nom");
		symboleLabel = new Label("symbole");
		boxPrincipale.getChildren().addAll(grille,nomLabel,symboleLabel);



		Pane p = new Pane();
		p.getChildren().add(boxPrincipale);

		ready = true;

		primaryStage.setTitle("Morpion");
		primaryStage.setScene(new Scene(p,700,700));
		primaryStage.show();

		tourOrdi();

		if (!g.draw(g.getP())){
			nomLabel.setText("Joueur " + playerCourant);
			symboleLabel.setText("Symbole : "+ players.get(this.playerCourant-1).getSymbol().getChar());
		}

	}

	public void lancer(String[] args, int X,int Y){
		this.x=X;
		this.y=Y;
		launch(args);

	}

	private void eventLabel(int i, int j){

		System.out.println("click enregistrer" + i +j);
		Plateau p = g.getP();

		for(Case c : p.getCasesPlateau())
		{
			if(c.getPosX() == i && c.getPosY() == j)
			{
				if(c.getStatut() != 0 ) {
					System.err.println("cette case est déjà joué");
					break;
				}else {
					p.getCase(i,j).setStatut(players.get(this.playerCourant-1).getSymbol().getVal());
					labels[i][j].setText(" " + players.get(this.playerCourant-1).getSymbol().getChar() + " ");


					if (testFin()){
						for (Label[] li : labels){
							for (Label l : li){
								//l.setDisable(true);
								l.setOnMouseClicked(e -> e.consume());
							}
						}
						break;
					}
					else {
						if (players.size() > playerCourant){
							System.out.println("prochain joueur");
							playerCourant++;
						}
						else {
							System.out.println("tour terminer");
							playerCourant = 1;
						}
						tourOrdi();

						nomLabel.setText("Joueur " + playerCourant);
						symboleLabel.setText("Symbole : "+ players.get(this.playerCourant-1).getSymbol().getChar());
					}


				}
			}
		}







	}

	private void tourOrdi(){
		while(true){
			//System.out.println("tour boucle ordi");
			if (players.get(this.playerCourant-1) instanceof Computer){
				Case c = players.get(this.playerCourant-1).play(g.getP(),this);
				c.setStatut(players.get(this.playerCourant-1).getSymbol().getVal());
				labels[c.getPosX()][c.getPosY()].setText(" " + players.get(this.playerCourant-1).getSymbol().getChar() + " ");

				if (testFin()){
					for (Label[] li : labels){
						for (Label l : li){
							//l.setDisable(true);
							l.setOnMouseClicked(e -> e.consume());
						}
					}
					break;
				}
				else {
					if (players.size() > playerCourant){
						playerCourant++;
					}
					else {
						playerCourant = 1;
					}
				}


			}
			else{
				break;
			}
		}
	}

	private boolean testFin(){
		Plateau p = g.getP();
		if (g.draw(p)){
			for(Player j : players) {
				j.calculScore(p);
			}

			Player winner = players.get(0);
			int score = 0;
			boolean egalite = false;

			for (Player j : players){
				if(j.getScore()>score){
					score = j.getScore();
					winner = j;
					egalite = false;
				} else if (j.getScore() == score) {
					egalite = true;
				}
			}

			if (egalite){
				nomLabel.setText("Egalité !");
				symboleLabel.setText("avec un score de " + score);
			}
			else {
				nomLabel.setText("Le gagnant est le symbole : " + winner.getSymbol().getChar());
				symboleLabel.setText("avec un score de " + score);
			}

			return true;
		}
		else {
			return false;
		}
	}

	public boolean getReady(){
		return ready;
	}

	public void aJouer(){
		jouer = false;
	}
	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public boolean isJouer() {
		return jouer;
	}

	public GridPane getGrille() {
		return grille;
	}

	public Label[][] getLabels() {
		return labels;
	}

	public Label getNomLabel() {
		return nomLabel;
	}

	public Label getSymboleLabel() {
		return symboleLabel;
	}
}
