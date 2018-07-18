package fr.formation.puissance4.logic;

public class Player {
    boolean[][] myMoves;

    public Player(){
        myMoves = new boolean[6][7];

    }

    public void setMyMoves(int x, int y){
        myMoves[x][y] = true;

    }

    private boolean checkWin(int x, int y){
        if(checkDiagonal(x,y))
            return true;
        if(checkVeritcal(x,y))
            return true;
        if(checkHorizontal(x,y))
            return true;

        return  false;

    }

    private boolean checkHorizontal(int x, int y){

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

    private boolean checkVeritcal (int x, int y){
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

    private boolean checkDiagonal(int x, int y){
        return false;
    }
}
