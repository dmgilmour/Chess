
public class Logic {

	private BoardPanel _boardPanel;

	private Player _white;
	private Player _black;
	private Player _curPlayer;
	private Player _opponent;
	private Piece[][] _board;

	private boolean _selectionBeenMade;
	private Piece _selectedPiece;

	public Logic(BoardPanel boardPanel, Player white, Player black, Piece[][] board) {
		_boardPanel = boardPanel;
		_white = white;
		_black = black;
		_curPlayer = _white;
		_opponent = _black;
		_board = board;
		_selectionBeenMade = false;
	}

	public Player getCurPlayer() {
		return _curPlayer;
	}

	public Player getOpponent() {
		return _opponent;
	}


	public void registerClick(Piece p) {
		if (p.getPlayer() != _curPlayer && !_selectionBeenMade) {
			// Not player's piece
		} else if (!_selectionBeenMade) {
			// Is players piece, being selected
			_selectionBeenMade = true;
			_selectedPiece = p;
			_boardPanel.highlightSquare(_selectedPiece);
		} else if (p == _selectedPiece) {
			// Selected previous piece, unselecting
			_boardPanel.unhighlightSquare(_selectedPiece);
			_selectionBeenMade = false;
			_selectedPiece = null;
		} else if (_selectionBeenMade && p.getPlayer() == _curPlayer) {
			// Attempting to select one of the player's pieces, blinking
			_boardPanel.blinkSquare(_selectedPiece);
		} else if (_selectedPiece.canMove(p.getRank(), p.getFile())) { 
			// Successful piece select
			int prevRank = _selectedPiece.getRank();
			int prevFile = _selectedPiece.getFile();

			
			if (!willCauseCheck(_selectedPiece, p)) {
				_selectedPiece.move(p.getRank(), p.getFile());
				// if (p.getPlayer() != _curPlayer) {
					p.remove();
				// }
				_boardPanel.update(_selectedPiece);
				_boardPanel.update(prevRank, prevFile);
				_boardPanel.unhighlightSquare(prevRank, prevFile);
				_selectedPiece = null;
				_selectionBeenMade = false;
				nextTurn();
				
			} else {
				System.out.println("Puts you in check");
				_boardPanel.blinkSquare(_curPlayer.getKing());
			}
		} else {
			_boardPanel.blinkSquare(_selectedPiece);
			System.out.println("Invalid movement for this piece");
		}
	}

	public void nextTurn() {
		System.out.println("Next Turn");
		System.out.println("White: " + _white.getPieces().size());
		System.out.println("Black: " + _black.getPieces().size());
		_opponent = _curPlayer;
		if (_curPlayer == _white) {
			_curPlayer = _black;
		} else {
			_curPlayer = _white;
		}
		_boardPanel.display(_curPlayer);
	}

	public boolean inCheck(Player player, Player opponent) {
		Piece king = player.getPieces().get(player.getPieces().size() - 1);
		for (Piece p : opponent.getPieces()) {
			if (p.canMove(king.getRank(), king.getFile())) {
				// Checks that the piece has not been captured this turn
				if (_board[p.getRank()][p.getFile()] == p) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean willCauseCheck(Piece toMove, Piece toTake) {

		int prevRank = toMove.getRank();
		int prevFile = toMove.getFile();

		int nextRank = toTake.getRank();
		int nextFile = toTake.getFile();

		_board[prevRank][prevFile] = new NullPiece(prevRank, prevFile, this);
		_board[nextRank][nextFile] = toMove;

		Player opponent = (toMove.getPlayer() == _curPlayer ? _opponent : _curPlayer);

		boolean toReturn = inCheck(toMove.getPlayer(), opponent);

		_board[prevRank][prevFile] = toMove;
		_board[nextRank][nextFile] = toTake;

		return toReturn;
	}

		
		

}
