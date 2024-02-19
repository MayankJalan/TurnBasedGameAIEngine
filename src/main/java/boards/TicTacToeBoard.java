package boards;

import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;

public class TicTacToeBoard extends Board {
     String cells[][] = new String[3][3];


     public String getCells(int i, int j) {
          return cells[i][j];
     }
     public void setCell(Cell cell, String symbol){
          cells[cell.getRow()][cell.getCol()]=symbol;
     }

     @Override
     public String toString() {
          String result="";
          for(int i=0;i<3;i++){
               for(int j=0;j<3;j++){
                    result+=cells[i][j]==null?"-" : cells[i][j];
               }
               result+="\n";
          }
          return result;
     }

     @Override
     public void move(Move move) {
          setCell(move.getCell(),move.getPlayer().symbol());
     }
}
