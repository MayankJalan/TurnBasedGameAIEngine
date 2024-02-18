import api.GameEngine;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;
import gamestate.Player;
import org.ietf.jgss.GSSManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine=new GameEngine();
        Board board=gameEngine.start("TicTacToe");
        Scanner in=new Scanner(System.in);

        while(!gameEngine.isComplete(board).isOver()){
            Player computer=new Player("O");
            Player opponent=new Player("X");

            System.out.println("Make your move!");
            int row=in.nextInt();
            int col= in.nextInt();
            Move oppMove=new Move(new Cell(row, col));
            gameEngine.move(board,opponent,oppMove);
            System.out.println(board);
            if(!gameEngine.isComplete(board).isOver()){
                Move computerMove=gameEngine.suggestMove(computer,board);
                gameEngine.move(board,computer,computerMove);
                System.out.println(board);


            }
        }

        System.out.println(gameEngine.isComplete(board));
        System.out.println(board);

    }
}
