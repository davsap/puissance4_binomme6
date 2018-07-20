package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Direction;
import fr.formation.puissance4.Position;
import javafx.scene.paint.Color;



public class JoueurIA extends Joueur {

    public JoueurIA(Color color, Board board){

        super(color,board);
    }

    private int maxAdverserHorizontal = 0;
    private int maxAdverserVertical = 0;
    private int maxAdverserDiagonal = 0;




    private Position guessBestPosition;

    @Override
    public String envoyer() {
        if(!estPlein()){



            Position position ;
            if (guessBestPosition == null)
                position = UtilRobot.createRandomPosition(board);
            else
                position = guessBestPosition;


            board.getJetons()[position.getX()][position.getY()].setColor(color);

            if(didIwin(position.getX(),position.getY())){
                return "Fin";
            }


            return position.getX() + "," + position.getY() + "," + (color.equals(Color.RED) ? "RED" : "YELLOW");
        }
        return "Fin";
    }

    @Override
    public void recevoir(String messageRecu) {
        if(!messageRecu.equals("Fin")) {
            String[] strings = messageRecu.split(",");
            int ligne = Integer.parseInt(strings[0]);
            int colonne = Integer.parseInt(strings[1]);
            Color adverserColor = Color.valueOf(strings[2]);

            if (!estPlein()) {
                board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));


            }
            guessBestPosition = getNextPosition(ligne,colonne,adverserColor);
            didIwin(ligne, colonne);
        }
    }

    private Position getNextPosition(int x, int y , Color color){
        Position horizental = getHorizontalAdverserCounterPosition(x,y,color);
       Position vertical = getVerticalAdverserCounterPosition(x,y,color);
      Position diagonal = getDiagonalAdverserCounterPosition(x,y,color);


        if(maxAdverserHorizontal > maxAdverserVertical ) {
            if (maxAdverserVertical >= maxAdverserDiagonal) {
                return horizental;
            }
        } else {
            if (maxAdverserDiagonal > maxAdverserHorizontal) {
                return diagonal;
            } else
                return vertical;

        }

        return UtilRobot.createRandomPosition(board);

    }


    private Position getVerticalAdverserCounterPosition(int adrverserX, int adverserY, Color adverserColor){
        Position position = null;
        int counter = 0;

        for(int i = 0; i < 5;i++){
           if(board.getJetons()[i][adverserY].getColor().equals(adverserColor))
               counter++;
        }

        if(counter >= 2){
            if( (position = getNextOpenPosition(adrverserX,adverserY,Direction.VERTICAL ))== null)
                position = getPreviuosOpenPosition(adrverserX,adverserY,Direction.VERTICAL);
            maxAdverserVertical = counter;
        }

        return position;
    }

    private Position getHorizontalAdverserCounterPosition(int adrverserX, int adverserY, Color adverserColor){
        Position position = null;
        int counter = 0;

        for(int i = 0; i < 6;i++){
            if(board.getJetons()[adrverserX][i].getColor().equals(adverserColor))
                counter++;
        }

        if(counter >= 2){
            if( (position = getNextOpenPosition(adrverserX,adverserY,Direction.HORIZONTAL ))== null)
                position = getPreviuosOpenPosition(adrverserX,adverserY,Direction.HORIZONTAL);
            maxAdverserHorizontal = counter;
        }

        return position;
    }

    private Position getDiagonalAdverserCounterPosition(int adrverserX, int adverserY, Color adverserColor){
        Position position = null;
        int counter = 0;
        int beginX = 0, beginY =0, endX =0,endY =0;

        int z =adrverserX  + adverserY;

        if( z <= 2 )
            return null;


         if( z >= 9)
            return null;

        if( z < 6){

            beginY = z;
            endX = z;
            endY = 0;
        }

        if( z >=6 ){
            beginX = z - 5 -1;
            beginY = 6;
            endX = 5;
            endY = 6;
        }

        int i = endX;
        int j = beginY;

        while (i >= beginX && j <= endY){
            if(board.getJetons()[i][j].getColor().equals(adverserColor)) {
                counter++;

            }
            i--;
            j++;
        }



        if(counter >= 2){
            if( (position = getNextOpenPosition(adrverserX,adverserY,Direction.DIAGONAL ))== null)
                position = getPreviuosOpenPosition(adrverserX,adverserY,Direction.DIAGONAL);
            maxAdverserDiagonal = counter;
        }

        return position;
    }

    private Position getNextOpenPosition(int x, int y, Direction direction){
        int i =0,j=0;
        switch (direction){
            case DIAGONAL:
                i = x-1;
                j = y+1;
                break;
            case COUNTERDIAGONAL:
                i = x +1;
                j = y - 1;
                break;
            case VERTICAL:
                i = x -1;
                j =y;
                break;
            case HORIZONTAL:
                i =x;
                j = y +1;
                break;

        }
        if( i >= 0 && i<=5 && j>=0 && j <=6 )
            if(board.getJetons()[i][j].getColor().equals(Color.TRANSPARENT)) {
                return new Position(i,j);
            }else
                return getNextOpenPosition(i,j, direction);

        return null;
    }

    private  Position getPreviuosOpenPosition(int x, int y,Direction direction){
        int i =0,j=0;
        switch (direction){
            case DIAGONAL:
                i = x+1;
                j = y-1;
                break;
            case COUNTERDIAGONAL:
                i = x -1;
                j = y + 1;
                break;
            case VERTICAL:
                i = x +1;
                j =y;
                break;
            case HORIZONTAL:
                i =x;
                j = y -1;
                break;

        }
        if( i >= 0 && i<=5 && j>=0 && j <=6 )
            if(board.getJetons()[i][j].getColor().equals(Color.TRANSPARENT)) {
                return new Position(i,j);
            }else
                return getPreviuosOpenPosition(i,j, direction);

        return null;
    }


    /*
   private Position getDiagonalReverseAdverserCounterPosition(int adrverserX, int adverserY, Color adverserColor){
        Position position = null;
        int counter = 0;
        int beginX = 0, beginY =0, endX =0,endY =0;

        int z =adrverserX  + adverserY;

        if( z <= 2 )
            return null;


         if( z >= 9)
            return null;

        if( z >2 && z < 6){
            beginX = 0;
            beginY = z;
            endX = z;
            endY = 0;
        }

        if( z >=6 && z < 9){
            beginX = z - 5 -1;
            beginY = 6;
            endX = 5;
            endY = 6;
        }

        int i = endX;
        int j = beginY;

        while (i >= beginX && j <= endY){
            if(board.getJetons()[i][j].getColor().equals(adverserColor)) {
                counter++;
                i--;
                j++;
            }
        }



        if(counter >= 2){
            if( (position = getNextOpenPosition(adrverserX,adverserY,Direction.HORIZONTAL ))== null)
                position = getPreviuosOpenPosition(adrverserX,adverserY,Direction.HORIZONTAL);
        }

        return position;
    }

*/
}
