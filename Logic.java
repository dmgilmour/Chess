
public class Logic {

	private boolean _selectionBeenMade;
	private Piece _selectedPiece;
	private BoardPanel _boardPanel;
	private Player _white;
	private Player _black;
	private Player _curPlayer;
	private Player _opponent;

	public Logic(BoardPanel boardPanel, Player white, Player black) {
		_boardPanel = boardPanel;
		_white = white;
		_black = black;
		_curPlayer = _white;
		_opponent = _black;
		_selectionBeenMade = false;
	}

	public Player getCurPlayer() {
		return _curPlayer;
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
			try {
				int rank = _selectedPiece.getRank();
				int file = _selectedPiece.getFile();
				_selectedPiece.move(p.getRank(), p.getFile());
				if (p.getPlayer() != _curPlayer) {
					p.remove();
				}
				if (inCheck(_curPlayer, _opponent)) {
					throw new Exception();
				} else {
					_boardPanel.update(p);
					_boardPanel.update(rank, file);
					_boardPanel.unhighlightSquare(rank, file);
					_selectionBeenMade = false;
					nextTurn();
				}
					
			} catch (Exception e) {
				System.out.println("Puts you in check");
				System.out.println(">" + _selectedPiece.toString());
				_boardPanel.blinkSquare(_curPlayer.getKing());
				_boardPanel.blinkSquare(_selectedPiece);
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
				return true;
			}
		}
		return false;
	}
		

}
