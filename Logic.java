
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


	public void registerClick(Piece p) {
		if (p.getPlayer() != _curPlayer && !_selectionBeenMade) {
			System.out.println("Not your piece");
		} else if (!_selectionBeenMade) {
			System.out.println("Piece Selected");
			_selectionBeenMade = true;
			_selectedPiece = p;
		} else if (p == _selectedPiece) {
			System.out.println("Undoing select");
			_selectionBeenMade = false;
			_selectedPiece = null;
		} else if (_selectionBeenMade && p.getPlayer() == _curPlayer) {
			System.out.println("Blocked by your piece");
		} else if (_selectedPiece.canMove(p.getRank(), p.getFile())) { 
			System.out.println("Second Click");
			try {
				_selectedPiece.move(p.getRank(), p.getFile());
				if (p.getPlayer() != _curPlayer) {
					p.remove();
				}
				if (inCheck(_curPlayer, _opponent)) {
					throw new Exception();
				} else {
					_selectionBeenMade = false;
					nextTurn();
				}
					
			} catch (Exception e) {
				System.out.println("Puts you in check");
			}
		} else {
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
		System.out.println(king);
		for (Piece p : opponent.getPieces()) {
			if (p.canMove(king.getRank(), king.getFile())) {
				return true;
			}
		}
		return false;
	}
		

}
