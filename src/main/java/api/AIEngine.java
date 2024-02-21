package api;

import boards.TicTacToeBoard;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;
import gamestate.Player;

public class AIEngine {
    public Move suggestMove(Player computer, Board board) {
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1=(TicTacToeBoard) board;
            Move suggestion;
            if(isStartingMoves(board1,3)){
                suggestion=getBasicMove(board1,computer);
            }
            else{
                suggestion=getSmartMoves(board1,computer);
            }
            if(suggestion!=null){
                return suggestion;
            }
            throw new IllegalStateException();
        }
        else{
            throw new IllegalStateException();
        }
    }

    private boolean isStartingMoves(TicTacToeBoard board1, int threshold) {
        int count=0;
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getCells(i, j) != null) {
                    count++;
                }
            }
        }
        return count<threshold;
    }

    private Move getSmartMoves(TicTacToeBoard board1, Player player) {
        RuleEngine ruleEngine=new RuleEngine();

        //Victorious Move
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getCells(i, j) == null) {
                    Move move=new Move(new Cell(i,j), player);
                    TicTacToeBoard boardCopy= board1.copy();
                    boardCopy.move(move);
                    if(ruleEngine.isComplete(boardCopy).isOver()){
                        return move;
                    }

                }
            }
        }

        //Defensive Move
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getCells(i, j) == null) {
                    Move move=new Move(new Cell(i,j), player.flip());
                    TicTacToeBoard boardCopy= board1.copy();
                    boardCopy.move(move);
                    if(ruleEngine.isComplete(boardCopy).isOver()){
                        return new Move(new Cell(i,j),player);
                    }

                }
            }
        }

        return null;



    }

    private Move getBasicMove(TicTacToeBoard board1, Player computer){
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getCells(i, j) == null) {
                    return new Move(new Cell(i, j),computer);
                }
            }
        }
        return null;
    }

}
