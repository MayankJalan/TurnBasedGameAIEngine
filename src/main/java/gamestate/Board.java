package gamestate;
import boards.TicTacToeBoard;
import gamestate.Move;

public interface Board {


    public abstract void move(Move move);

    public abstract TicTacToeBoard copy();
}
