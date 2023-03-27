package com.example.jfxmorpion.morpion;

import javafx.application.Application;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Choix de la taille du tableau");

		System.out.println("Choix des X (lignes)");
		
		Scanner size_x = new Scanner(System.in);
		int tailleX = size_x.nextInt();
		
		System.out.println("Choix des Y (colonnes)");
		
		Scanner size_y = new Scanner(System.in);
		int tailleY = size_y.nextInt();



		Game g;
		g = new Game(args);
		g.initGame(tailleX, tailleY);
		g.play();

		size_x.close();
		size_y.close();
		

	}



}
