package server;
import java.io.Serializable;
public class PipeGameBoard implements Board<Character>, Serializable {
	private char[][] board;
	//public PipeGameBoard() {}
	PipeGameBoard(char[][] board) {
		this.board = board;
	}
	@Override
	public Character getHieghtWidth(Integer x, Integer y) {
		return this.board[y][x];
	}
	@Override
	public Integer getH() {
		return this.board.length;
	}
	@Override
	public Integer getW() {
		return this.board[0].length;
	}

}
