package fr.formation.puissance4.logic;

public class PathTree {

    private Node root;
    private Position[] positions;


    private void init(){
        positions = new Position[42];
        int positionIndex = 0;

        for(int i = 1; i <=6; i++){
            for(int j = 1; j <= 7;j++){
                Position pos = new Position(i,j);
                positions[positionIndex] = pos;
                positionIndex++;
                System.out.println(pos);
            }
        }
    }

    private void createPath(){

    }
}
