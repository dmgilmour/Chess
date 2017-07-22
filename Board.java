
public class Board {
	
	BoardPanel _boardPanel;
	Piece[][] _board;

	public Board(BoardPanel boardPanel, Logic logic) {
		_board = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				_board[i][j] = new NullPiece(i, j, logic);
			}
		}
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
		

