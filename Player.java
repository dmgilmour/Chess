import java.util.ArrayList;

public class Player {

	private ArrayList<Piece> _pieces;
	private int _num;
	private String _str;

	public Player(String str, int num) {
		_str = str;
		_num = num;
		_pieces = new ArrayList<Piece>(16);
	}

	public void initializePieces(Piece[][] board, Logic logic) {
		for (int i = 0; i < 8; i++) {
			_pieces.add(new Pawn(((_num == 0) ? 1 : 6), i, this, board, logic));	
		}
		_pieces.add(new Rook(((_num == 0) ? 0 : 7), 0, this, board, logic));
		_pieces.add(new Rook(((_num == 0) ? 0 : 7), 7, this, board, logic));
		_pieces.add(new Knight(((_num == 0) ? 0 : 7), 1, this, board, logic));
		_pieces.add(new Knight(((_num == 0) ? 0 : 7), 6, this, board, logic));
		_pieces.add(new Bishop(((_num == 0) ? 0 : 7), 2, this, board, logic));
		_pieces.add(new Bishop(((_num == 0) ? 0 : 7), 5, this, board, logic));
		_pieces.add(new Queen(((_num == 0) ? 0 : 7), 4, this, board, logic));
		_pieces.add(new King(((_num == 0) ? 0 : 7), 3, this, board, logic));
		placePieces(board);
	}

	public int getNum() {
		return _num;
	}

	public String toString() {
		return _str;
	}

	public ArrayList<Piece> getPieces() {
		return _pieces;
	}

	public Piece getKing() {
		return _pieces.get(_pieces.size() - 1);
	}

	public void placePieces(Piece[][] board) {
		for (Piece p : _pieces) {
			board[p.getRank()][p.getFile()] = p;
		}
			
	}
}
		
		
