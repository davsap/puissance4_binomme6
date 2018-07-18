package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Exception.LigneChoisiException1;
import javafx.scene.paint.Color;

import java.util.Scanner;

public class JoueurHumain extends Joueur {
    public JoueurHumain(Color color, Board board) {
        super(color, board);
    }


    public int choisirColonne() {
        System.out.println(" Entrez un nombre entre 1 et 6:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt()-1;

    }

    public int choisirLigne(int colonne) throws LigneChoisiException1 {
        int ligne;
        for (ligne = -1; ligne < board.getJetons().length-1; ligne++) {
            if (!board.getJetons()[ligne + 1][colonne].getColor().equals(Color.TRANSPARENT)) {
                if (ligne ==-1) {
                    throw new LigneChoisiException1();
                }
                return ligne;
            }
        }
        return ligne;
    }


    /*  public boolean estPlein() {
        int grille[][]= new int [6][7];

        for(int colonne=0; colonne<6; colonne++){
            for(int ligne=0; ligne<7; ligne++){
             if(grille[colonne][ligne]){
                 return false;
             }
            }

        }
        return true;
    }*/


    public String choisirColor() {
        if (color.equals(Color.RED))
            return "RED";
        else
            return "YELLOW";
    }

    @Override
    public String envoyer() {

        int colonne = choisirColonne();
        int ligne = 0;
        try {
            ligne = choisirLigne(colonne);
        } catch (LigneChoisiException1 ligneChoisiException1) {
            ligneChoisiException1.printStackTrace();
        }

        board.getJetons()[ligne][colonne].setColor(color);
        return ligne + "," + colonne + "," + choisirColor();
    }

    @Override
    public void recevoir(String messageRecu) {
        String[] strings = messageRecu.split(",");
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }
}