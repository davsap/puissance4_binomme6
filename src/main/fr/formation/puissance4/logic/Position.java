package fr.formation.puissance4.logic;

public class Position {
    private int x;
    private  int y;

    public Position(int x,int y){
        this.x = x;
        this.y = y;

    }

    @Override
    public String toString() {
        return String.format("|%3d|%3d",x,y);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;
        if(obj == null || obj.getClass() != this.getClass())
            return  false;

        Position position =(Position)obj;
        return ( this.x == position.x && this.y == position.y);


    }
}
