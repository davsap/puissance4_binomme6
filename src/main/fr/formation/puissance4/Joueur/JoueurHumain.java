package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Exception.IntputMismatchException;
import fr.formation.puissance4.Exception.LigneChoisiException1;
import javafx.scene.paint.Color;

import java.util.InputMismatchException;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class JoueurHumain extends Joueur {
    public JoueurHumain(Color color, Board board) {
        super(color, board);
    }


    public int choisirColonne()throws IntputMismatchException {


            System.out.println(" Entrez un nombre entre 1 et 6:");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt()- 1;

            if(input < 0 || input >6)
                throw new InputMismatchException(" Le nombre est invalide");

            return input;
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

    public boolean estPlein() {
        int grille[][] = new int[6][7];

        for (int colonne = 0; colonne < 6; colonne++) {
            for (int ligne = 0; ligne < 7; ligne++) {
                if (board.getJetons()[ligne][colonne].getColor().equals(Color.TRANSPARENT)) {
                    return false;
                }
            }
        }
        return true;
    }


    public String choisirColor() {
        if (color.equals(Color.RED))
            return "RED";
        else
            return "YELLOW";
    }

    @Override
    public String envoyer() {

        int colonne = 0;
        try {
            colonne = choisirColonne();
        } catch (IntputMismatchException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" Index est trop grand ");
        }
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