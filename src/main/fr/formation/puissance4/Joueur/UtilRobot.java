package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Position;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

import java.util.Random;

public class UtilRobot {


    private static int getLine(int column, Board board){
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

    public static Position createRandomPosition(Board board){
        Position position ;
        int y = 0;
        int x =0;

        boolean found = false;

        while ( !found){
            y = (new Random().nextInt(7) + 1);
            y = y -1;

            if((x=getLine(y,board)) > -1)
                found = true;

        }

       position = new Position(x,y);

        return position;
    }
}
