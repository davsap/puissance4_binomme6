package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

import java.util.Random;

public class JoueurRobotRandom extends Joueur {


    public JoueurRobotRandom(Color color, Board board){
        super(color,board);
    }

    @Override
    public String envoyer() {


        if(!estPlein()){
            int position[] = createRandomPosition();

            board.getJetons()[position[0]][position[1]].setColor(color);

            if(didIwin(position[0],position[1])){
                return "Fin";
            }


            return position[0] + "," + position[1] + "," + (color.equals(Color.RED) ? "RED" : "YELLOW");
        }
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

    private  int[] createRandomPosition(){
        int xy[] = new int[2];
        int y = 0;
        int x =0;

        boolean found = false;

        while ( !found){
             y = (new Random().nextInt(7) + 1);
            y = y -1;

             if((x=getLine(y)) > -1)
                found = true;

        }

        xy[0] = x;
        xy[1] = y;

        return xy;
    }

    private int getLine(int column){
        int line = -1;

        int index=5;
        boolean find = false;

        while(index < board.getJetons()[index].length && (!find)){
            if(board.getJetons()[index][column].getColor().equals(Color.TRANSPARENT)){
                line = index;
                find = true;
            }
            index--;
        }
        return line;
    }



}
