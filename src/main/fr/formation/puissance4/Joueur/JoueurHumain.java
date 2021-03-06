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


    public int choisirColonne() throws IntputMismatchException {


        System.out.println(" Entrez un nombre entre 1 et 6:");
        Scanner scanner = new Scanner(System.in);
        String strInput = scanner.nextLine();
        int input;

        if (!strInput.matches("^\\d+$"))
            throw new InputMismatchException(" Ce n'est pas un nombre");
        else {
            input = Integer.parseInt(strInput) - 1;
            if (input < 0 || input > 6)
                throw new InputMismatchException(" Le nombre est invalide");
        }
        return input;
    }

    public int choisirLigne(int colonne) throws LigneChoisiException1 {
        int ligne;
        for (ligne = -1; ligne < board.getJetons().length - 1; ligne++) {
            if (!board.getJetons()[ligne + 1][colonne].getColor().equals(Color.TRANSPARENT)) {
                if (ligne == -1) {
                    throw new LigneChoisiException1();
                }
                return ligne;
            }
        }
        return ligne;
    }

    public String choisirColor() {
        if (color.equals(Color.RED))
            return "RED";
        else
            return "YELLOW";
    }

    /*
    protected boolean estPlein() {
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
*/
    @Override
    public String envoyer() {
        int ligne = 0;
        int colonne = 0;
        int compteur = 0;
        do {
            try {
                colonne = choisirColonne();
                ligne = choisirLigne(colonne);
                board.getJetons()[ligne][colonne].setColor(color);

                if(didIwin(ligne,colonne)){
                    return "Fin";
                }


                return ligne + "," + colonne + "," + choisirColor();

            } catch (IntputMismatchException | LigneChoisiException1 e) {
                e.printStackTrace();

            }
        } while (++compteur < 7);

        return "Fin";
    }

    @Override
    public void recevoir(String messageRecu) {
        if(!messageRecu.equals("Fin")) {
            String[] strings = messageRecu.split(",");
            int ligne = Integer.parseInt(strings[0]);
            int colonne = Integer.parseInt(strings[1]);


            if (!estPlein())
                board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));

            didIwin(ligne, colonne);
        }
    }
}