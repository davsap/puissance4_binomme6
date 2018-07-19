package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

public abstract class Joueur {
    protected Color color;
    protected Board board;
    boolean[][] myMoves;



    public Joueur(Color color, Board board) {
        this.color = color;
        this.board = board;
        myMoves = new boolean[6][7];


    }

    public abstract String envoyer();

    public abstract void recevoir(String messageRecu);

    public Color getColor() {
        return color;
    }

    public Board getBoard() {
        return board;
    }


    protected boolean checkHorizontal(int x, int y){

        boolean result = false;
        int begin = y <=3 ? 0 : y-3;
        int end = y <=3 ? y+3 : 6;
        int check = begin;

        while( !result && check < end ){

            for(int i=begin;i<check+4;i++){
                result &= myMoves[x][i];
            }
            begin++;
            check++;
        }

        return result;
    }
    protected boolean checkVeritcal (int x, int y){
        boolean result = false;
        int begin = x <=3 ? 0 : y-3;
        int end = x <=3 ? x+3 : 5;
        int check = begin;

        while( !result && check < end ){

            for(int i=begin;i<check+4;i++){
                result &= myMoves[i][y];
            }
            begin++;
            check++;
        }

        return result;
    }

    protected boolean checkDiagonal(int x, int y){
        return false;
    }

    protected void setMyMoves(int x, int y){
        myMoves[x][y] = true;

    }
}
