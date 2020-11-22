package Model;

import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {

    private boolean playerTurn;

    private List<Cell> board;
    private int[][] boardState;

    public GameState(List<Cell> board, int[][] boardState, boolean playerTurn) {
        this.board = board;
        this.boardState = boardState;
        this.playerTurn = playerTurn;
    }

    public List<Cell> getBoard() {
        return board;
    }

    public int[][] getBoardState() {
        return boardState;
    }

    public boolean getPlayerTurn() {
        return playerTurn;
    }
}