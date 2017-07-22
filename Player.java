import java.util.ArrayList;

public class Player {

	private ArrayList<Piece> _pieces;
	private int _num;

	public Player(int num) {
		_num = num;
		_pieces = new ArrayList<Piece>(16);
	}

	public void initializePieces(Board board) {
		for (int i = 0; i < 8; i++) {
			_pieces.add(new Pawn(((_num == 0) ? 1 : 6), i, this, board));	
		}
		_pieces.add(new Rook(((_num == 0) ? 0 : 7), 0, this, board));
		_pieces.add(new Rook(((_num == 0) ? 0 : 7), 7, this, board));
		_pieces.add(new Knight(((_num == 0) ? 0 : 7), 1, this, board));
		_pieces.add(new Knight(((_num == 0) ? 0 : 7), 6, this, board));
		_pieces.add(new Bishop(((_num == 0) ? 0 : 7), 2, this, board));
		_pieces.add(new Bishop(((_num == 0) ? 0 : 7), 5, this, board));
		_pieces.add(new Queen(((_num == 0) ? 0 : 7), 3, this, board));
		_pieces.add(new King(((_num == 0) ? 0 : 7), 4, this, board));
	}

	public int getNum() {
		return _num;
	}

	public ArrayList<Piece> getPieces() {
		return _pieces;
	}
}
		
		
