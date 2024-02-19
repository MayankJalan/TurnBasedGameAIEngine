import api.AIEngine;
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
    AIEngine aiEngine;

    @Before
    public void setup(){
        gameEngine=new GameEngine();
        ruleEngine=new RuleEngine();
        aiEngine=new AIEngine();

    }
    @Test
    public void testForRowWin() {
        Board board=gameEngine.start("TicTacToe");
        int moves[][]=new int[][]{{1,0},{1,1},{1,2}};
        playGame(board,moves);
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("X"));
    }

    @Test
    public void testForColWin() {
        Board board=gameEngine.start("TicTacToe");
        int moves[][]=new int[][]{{0,0},{1,0},{2,0}};
        playGame(board,moves);
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("X"));
    }

    @Test
    public void testForDiagonalWin() {
        Board board=gameEngine.start("TicTacToe");
        int moves[][]=new int[][]{{0,0},{1,1},{2,2}};
        playGame(board,moves);
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("X"));
    }

    @Test
    public void testForReverseDiagonalWin(){
        Board board=gameEngine.start("TicTacToe");
        int moves[][]=new int[][]{{2,0},{1,1},{0,2}};
        playGame(board,moves);
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("X"));
    }
    @Test
    public void testForComputerWin(){
        Board board=gameEngine.start("TicTacToe");
        int moves[][]=new int[][]{{1,0},{1,1},{2,0}};
        playGame(board,moves);
        assertTrue(ruleEngine.isComplete(board).isOver());
        assertEquals(ruleEngine.isComplete(board).getWinner(),("O"));
    }



    public void playGame(Board board, int [][] moves){
        int next=0;
        while(!ruleEngine.isComplete(board).isOver()){
            Player computer=new Player("O");
            Player human=new Player("X");

            int row=moves[next][0];
            int col=moves[next][1];
            next++;
            Move oppMove=new Move(new Cell(row, col),human);
            gameEngine.move(board,oppMove);
            if(!ruleEngine.isComplete(board).isOver()){
                Move computerMove=aiEngine.suggestMove(computer,board);
                gameEngine.move(board,computerMove);
            }
        }
    }




}
