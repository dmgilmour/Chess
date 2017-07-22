
public class Logic {

	private boolean _selectionBeenMade;
	private Piece _selectedPiece;
	private BoardPanel _boardPanel;
	private Player _white;
	private Player _black;
	private Player _curPlayer;

	public Logic(BoardPanel boardPanel, Player white, Player black) {
		_boardPanel = boardPanel;
		_white = white;
		_black = black;
		_curPlayer = _white;
		_selectionBeenMade = false;
	}


	public void registerClick(Piece p) {
		if (p.getPlayer() != _curPlayer && !_selectionBeenMade) {
			System.out.println("Nope");
		} else if (!_selectionBeenMade) {
			System.out.println("Piece Selected");
			_selectionBeenMade = true;
			_selectedPiece = p;
		} else if (p == _selectedPiece) {
			System.out.println("Undoing select");
			_selectionBeenMade = false;
			_selectedPiece = null;
		} else if (_selectionBeenMade && p.getPlayer() == _curPlayer) {
			System.out.println("Nope");
		} else if (_selectedPiece.canMove(p.getRank(), p.getFile())) { 
			System.out.println("Second Click");
			_selectedPiece.move(p.getRank(), p.getFile());
			if (p.getPlayer() != _curPlayer) {
		 		p.remove();
			}
			_selectionBeenMade = false;
			nextTurn();
		} else {
			System.out.println("Fail");
		}
	}

	public void nextTurn() {
		System.out.println("Next Turn");
		System.out.println("White: " + _white.getPieces().size());
		System.out.println("Black: " + _black.getPieces().size());
		if (_curPlayer == _white) {
			_curPlayer = _black;
		} else {
			_curPlayer = _white;
		}
		_boardPanel.display(_curPlayer);
	}
}
