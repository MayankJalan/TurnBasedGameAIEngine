package api;

import boards.TicTacToeBoard;
import gamestate.Board;
import gamestate.Move;

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

    public void move(Board board, Move move){
        if(board instanceof TicTacToeBoard){
            board.move(move);
        }
        else {
            throw new IllegalArgumentException("wrong Game selected");
        }

    }

}

