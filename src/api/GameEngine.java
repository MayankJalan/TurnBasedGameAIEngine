package api;

import boards.TicTacToeBoard;
import gamestate.*;

import javax.security.auth.kerberos.KerberosTicket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GameEngine {
    public Board start(String type){

        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        }
        else {
            throw new IllegalArgumentException("wrong Game selected");
        }
    }

    public void move(Board board, Player player, Move move){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1=(TicTacToeBoard) board;
            board1.setCell(Move.getCell(), player.symbol());
        }
        else {
            throw new IllegalArgumentException("wrong Game selected");
        }

    }

    public GameResult isComplete(Board board){
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1=(TicTacToeBoard) board;
            boolean rowComplete =true;
            String firstCharacter="-";
            for(int i=0 ; i<3;i++){
                firstCharacter=board1.getCells(i,0);
                rowComplete=firstCharacter !=null;
                if(firstCharacter !=null) {
                    for (int j = 1; j < 3; j++) {
                        if (! firstCharacter.equals(board1.getCells(i, j))) {
                            rowComplete = false;
                            break;
                        }
                    }
                }
                if(rowComplete){
                    break;
                }
            }
            if(rowComplete){
                return new GameResult(true,firstCharacter);
            }

            boolean colComplete =true;
            for(int j=0 ; j<3;j++){
                firstCharacter=board1.getCells(0,j);
                colComplete=firstCharacter != null;
                if(firstCharacter != null) {
                    for (int i = 1; i < 3; i++) {
                        if (!firstCharacter.equals(board1.getCells(i, j))) {
                            colComplete = false;
                            break;
                        }
                    }
                    if (colComplete) {
                        break;
                    }
                }
            }
            if(colComplete){
                return new GameResult(true,firstCharacter);
            }

            firstCharacter=board1.getCells(0,0);
            boolean diagComplete =firstCharacter != null;
            if(firstCharacter != null) {
                for (int i = 1; i < 3; i++) {
                    if (!firstCharacter.equals(board1.getCells(i, i))) {
                        diagComplete = false;
                        break;
                    }
                }

                if (diagComplete) {
                    return new GameResult(true, firstCharacter);
                }
            }

            firstCharacter=board1.getCells(0,2);
            boolean revDiagComplete =firstCharacter != null;

            if(firstCharacter != null) {
                for (int i = 1; i < 3; i++) {
                    if (!firstCharacter.equals(board1.getCells(i, 2 - i))) {
                        revDiagComplete = false;
                        break;
                    }
                }
                if (revDiagComplete) {
                    return new GameResult(true, firstCharacter);
                }
            }

            int countOfFileldCells=0;

            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board1.getCells(i,j) !=null){
                        countOfFileldCells++;
                    }
                }
            }

            if(countOfFileldCells == 9){
                return new GameResult(true,"-");
            }
            else{
                return new GameResult(false,"-");

            }
            
        }
        else{
            return new GameResult(false,"-");
        }
    }

    public Move suggestMove(Player computer, Board board) {
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1=(TicTacToeBoard) board;
            for(int i=0;i<3;i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCells(i, j) == null) {
                        return new Move(new Cell(i, j));
                    }
                }
            }
            throw new IllegalStateException();
        }
        else{
            throw new IllegalStateException();
        }
    }
}

