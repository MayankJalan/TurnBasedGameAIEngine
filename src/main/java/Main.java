import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;
import gamestate.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine=new GameEngine();
        AIEngine aiEngine=new AIEngine();
        RuleEngine ruleEngine=new RuleEngine();
        Board board=gameEngine.start("TicTacToe");
        Scanner in=new Scanner(System.in);

        while(!ruleEngine.isComplete(board).isOver()){
            Player computer=new Player("O");
            Player human=new Player("X");

            System.out.println("Make your move!");
            int row=in.nextInt();
            int col= in.nextInt();
            Move oppMove=new Move(new Cell(row, col),human);
            gameEngine.move(board,oppMove);
            System.out.println(board);
            if(!ruleEngine.isComplete(board).isOver()){
                Move computerMove=aiEngine.suggestMove(computer,board);
                gameEngine.move(board,computerMove);
                System.out.println(board);
            }
        }

        System.out.println(ruleEngine.isComplete(board));
        System.out.println(board);

    }
}
