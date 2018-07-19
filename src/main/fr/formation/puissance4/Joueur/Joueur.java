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


    protected int checkWinDiagonal(int x, int y){
      int compteur = 0;
        int i =x;
        int j=y;
      boolean leMemeCoueur = true;


        while (i >=0 && j <=6 && leMemeCoueur) {
            if (leMemeCoueur = this.board.getJetons()[i][j].getColor() == color) {
                compteur++;

            }
            i--;
            j++;

        }

        if(compteur < 4) {
            i = x -1 ;
            j = y + 1;
            while (i <= 5 && j >= 0 && leMemeCoueur) {
                if (leMemeCoueur = this.board.getJetons()[i + 1][j - 1].getColor() == color) {
                    compteur++;
                }

                i++;
                j--;
            }
        }

      return compteur;

    }

    protected int checkWinDiagonalReverse(int x, int y){
      int compteur = 0;
        int i =x;
        int j=y;
      boolean leMemeCoueur = true;



        while (i <=5 && j <= 6 && leMemeCoueur) {
            if (leMemeCoueur = this.board.getJetons()[i][j].getColor() == color) {
                compteur++;
            }
            i++;
            j++;
        }

        if(compteur < 4) {
        i = x - 1;
        j = y - 1;
        while (i >= 0 && j >= 0 && leMemeCoueur) {
            if (leMemeCoueur = this.board.getJetons()[i][j].getColor() == color) {
                compteur++;
            }
            i--;
            j--;

        }
    }
      return compteur;

    }


    protected int checkWinVertical(int x, int y) {
        int compteur = 0;
        int i = x;

        boolean leMemeCoueur = true;


        while (i <= 5 && leMemeCoueur) {
            if (leMemeCoueur = this.board.getJetons()[i][y].getColor() == color) {
                compteur++;
            }
            i++;
        }

        if (compteur < 4) {
            i = x -1;
            while (i >= 0 && leMemeCoueur) {
                if (leMemeCoueur = this.board.getJetons()[i][y].getColor() == color) {
                    compteur++;
                }

                i--;

            }
        }
        return compteur;


    }

  protected int checkWinHorizontal(int x, int y){
      int compteur = 0;
        int i =y;

      boolean leMemeCoueur = true;


        while (i <= 6 && leMemeCoueur) {
            if (leMemeCoueur = this.board.getJetons()[x][i].getColor() == color) {
                compteur++;
            }
            else
                leMemeCoueur = false;
            i++;
        }
        if(compteur < 4)  {
            i = y-1;
            while (i >=0  && leMemeCoueur) {
                if (leMemeCoueur = this.board.getJetons()[x][i].getColor() == color) {
                    compteur++;
                }
                else
                    leMemeCoueur = false;

            }
            i--;
        }
      return compteur;

    }

    protected boolean didIwin(int ligne, int colonne){
        int point =0;

        point = checkWinHorizontal(ligne,colonne);



      if(point < 4)
            point = checkWinVertical(ligne,colonne);

        if(point < 4)

            point = checkWinDiagonal(ligne,colonne);

        if(point < 4)
            point = checkWinDiagonalReverse(ligne,colonne);





        System.out.println("nombre de point : " + point);

        if(point >= 4)
            System.out.println("ha ha j'ai gagné");

        return (point >=4);
    }

}
