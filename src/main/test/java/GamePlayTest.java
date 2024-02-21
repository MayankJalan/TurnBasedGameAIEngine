import api.GameEngine;
import api.RuleEngine;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;
import gamestate.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine=new GameEngine();
        ruleEngine=new RuleEngine();

    }
    @Test
    public void testForRowWin() {
        Board board=gameEngine.start("TicTacToe");
        int firstPlayerMoves[][]=new int[][]{{1,0},{1,1},{1,2}};
        int secondPlayerMoves[][]=new int[][]{{0,0},{2,1},{2,2}};
        playGame(board,firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("X"));
    }

    @Test
    public void testForColWin() {
        Board board=gameEngine.start("TicTacToe");
        int moves[][]=new int[][]{{0,0},{1,0},{2,0}};
        int secondPlayerMoves[][]=new int[][]{{1,2},{1,1},{2,2}};
        playGame(board,moves,secondPlayerMoves );
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("X"));
    }

    @Test
    public void testForDiagonalWin() {
        Board board=gameEngine.start("TicTacToe");
        int moves[][]=new int[][]{{0,0},{1,1},{2,2}};
        int secondPlayerMoves[][]=new int[][]{{1,0},{2,1},{1,2}};
        playGame(board,moves,secondPlayerMoves);
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("X"));
    }

    @Test
    public void testForReverseDiagonalWin(){
        Board board=gameEngine.start("TicTacToe");
        int moves[][]=new int[][]{{2,0},{1,1},{0,2}};
        int secondPlayerMoves[][]=new int[][]{{1,0},{0,1},{1,2}};
        playGame(board,moves,secondPlayerMoves );
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("X"));
    }
    @Test
    public void testForComputerWin(){
        Board board=gameEngine.start("TicTacToe");
        int moves[][]=new int[][]{{1,0},{1,1},{2,0}};
        int secondPlayerMoves[][]=new int[][]{{0,0},{0,1},{0,2}};
        playGame(board,moves,secondPlayerMoves);
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("O"));
    }

    public void playGame(Board board, int [][] firstPlayerMoves, int[][] secondPlayerMoves){
        int next=0;
        while(!ruleEngine.isComplete(board).isOver()){
            Player computer=new Player("O");
            Player human=new Player("X");

            int row= firstPlayerMoves[next][0];
            int col= firstPlayerMoves[next][1];

            Move oppMove=new Move(new Cell(row, col),human);
            gameEngine.move(board,oppMove);
            if(!ruleEngine.isComplete(board).isOver()){
                int secondPlayerRow=secondPlayerMoves[next][0];
                int secondPlayerCol=secondPlayerMoves[next][1];

                Move computerMove=new Move(new Cell(secondPlayerRow, secondPlayerCol),computer);

                gameEngine.move(board,computerMove);
            }
            next++;
        }
    }




}
