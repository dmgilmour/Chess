
public class Board {
	
	BoardPanel _boardPanel;
	Piece[][] _board;

	public Board(BoardPanel boardPanel) {
		_board = new Piece[8][8];
	}

	public Piece[][] getBoard() {
		return _board;
	}

	public void setPlayerPieces(Player player) {
		for (Piece p : player.getPieces()) {
			_board[p.getRank()][p.getFile()] = p;
		}
	}
}
		

