package com.example.jfxmorpion.morpion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class app extends Application {

    public boolean ready = false;
    VBox boxPrincipale;
    GridPane grille;

    Label[][] labels;
    Label nomLabel,symboleLabel;

    int coordX,coordY;
    boolean jouer = false;

    /*public static void main(String[] args) {
        launch(args);
    }*/
    int x,y;
    public app(/*int X, int Y*/){
        super();
        /*x=X;
        y=Y;
        grille = new GridPane();
        labels = new Label[X][Y];
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                Label l = new Label();
                labels[i][j] = l;
                grille.add(l,i,j);
                int finalI = i;
                int finalJ = j;
                l.setOnMouseClicked(e -> eventLabel(finalI, finalJ));
            }
        }*/
    }

    public void lancer(String[] args, int X , int Y){
        launch(args);
        x=X;
        y=Y;
    }

    private void eventLabel(int i, int j){
        coordX = i;
        coordY = j;
        jouer = true;
    }

    @Override
    public void start(Stage primaryStage) {
        grille = new GridPane();
        grille.setGridLinesVisible(true);
        labels = new Label[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Label l = new Label();
                labels[i][j] = l;
                grille.add(l,i,j);
                int finalI = i;
                int finalJ = j;
                l.setOnMouseClicked(e -> eventLabel(finalI, finalJ));
            }
        }
        boxPrincipale = new VBox();

        nomLabel = new Label();
        symboleLabel = new Label();
        boxPrincipale.getChildren().addAll(grille,nomLabel,symboleLabel);

        Pane p = new Pane();
        p.getChildren().add(boxPrincipale);

        primaryStage.setTitle("Morpion");
        primaryStage.setScene(new Scene(p,500,500));
        primaryStage.show();

        //ready = true;
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
