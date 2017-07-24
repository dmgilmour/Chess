import java.util.ArrayList;

public class Logic {

	private BoardPanel _boardPanel;

	private Player _curPlayer;
	private Player _opponent;
	private Piece[][] _board;

	private boolean _selectionBeenMade;
	private Piece _pieceToMove;

	public Logic(BoardPanel boardPanel, Player white, Player black, Piece[][] board) {
		_boardPanel = boardPanel;
		_curPlayer = white;
		_opponent = black;
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

			// First select and not player's piece

		} else if (!_selectionBeenMade) {

			// First select and is players piece, being selected

			_selectionBeenMade = true;
			_pieceToMove = p;
			_boardPanel.highlightSquare(_pieceToMove);

		} else if (p == _pieceToMove) {

			// Second select and selected piece to move, unselecting 

			_boardPanel.unhighlightSquare(_pieceToMove);
			_selectionBeenMade = false;
			_pieceToMove = null;

		} else if (_selectionBeenMade && p.getPlayer() == _curPlayer) {

			// Second select and selected another piece they own, changing selected piece 

			_boardPanel.unhighlightSquare(_pieceToMove.getRank(), _pieceToMove.getFile());
			_pieceToMove = p;
			_boardPanel.highlightSquare(_pieceToMove.getRank(), _pieceToMove.getFile());

		} else if (_pieceToMove.canMove(p.getRank(), p.getFile())) { 

			// Second select and chosen a valid square, checking if causes check

			int prevRank = _pieceToMove.getRank();
			int prevFile = _pieceToMove.getFile();
			
			if (!willCauseCheck(_pieceToMove, p)) {

				// Does not cause check, moving piece

				_pieceToMove.move(p.getRank(), p.getFile());
				p.remove();

				_boardPanel.update(_pieceToMove);
				_boardPanel.update(prevRank, prevFile);
				_boardPanel.unhighlightSquare(prevRank, prevFile);

				_pieceToMove = null;
				_selectionBeenMade = false;
				nextTurn();
			} else {

				// Causes check, displaying error

				_boardPanel.blinkSquare(_curPlayer.getKing());
			}

		} else {

			// Piece unable to move to square

			_boardPanel.blinkSquare(_pieceToMove);

		}
	}

	private void nextTurn() {

		// Check for mate
		Player tempPlayer = _curPlayer;
		_curPlayer = _opponent;
		_opponent = tempPlayer;
		_boardPanel.display(_curPlayer);

	}

	private boolean inCheck(Player player) {

		Player opponent = (player == _curPlayer ? _opponent : _curPlayer);

		Piece king = player.getKing();

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

	private boolean willCauseCheck(Piece toMove, Piece toTake) {

		int prevRank = toMove.getRank();
		int prevFile = toMove.getFile();

		int nextRank = toTake.getRank();
		int nextFile = toTake.getFile();

		_board[prevRank][prevFile] = new NullPiece(prevRank, prevFile, this);
		_board[nextRank][nextFile] = toMove;

		boolean toReturn = inCheck(toMove.getPlayer());

		_board[prevRank][prevFile] = toMove;
		_board[nextRank][nextFile] = toTake;

		return toReturn;
	}

	private boolean isCheckmate(Player player) {
		
		if (!inCheck(player)) {
			return false;
		} else {

			ArrayList<Piece> attackingPieces = getPiecesChecking(player);

			// Try moving the king
			Piece king = player.getKing();
			for (Piece sq : king.getAvailableMoves()) {
				if (!willCauseCheck(king, sq)) {
					return false;
				}
			}

			if (attackingPieces.size() > 1) {

				// You're dead

			} else {

				Piece attacker = attackingPieces.get(0);

				// Get spaces that could prevent attack
				// for pieces see if they can move to any space
				// for each space, see if it would cause check
				// if it does not cause check, not mate
				// if it does cause check, try again

			}
		}
		return false;
	}

	private ArrayList<Piece> getPiecesChecking(Player player) {

		Player opponent = (player == _curPlayer ? _opponent : _curPlayer);

		ArrayList<Piece> toReturn = new ArrayList<Piece>();	

		Piece king = player.getKing();

		for (Piece p : opponent.getPieces()) {
			if (p.canMove(king.getRank(), king.getFile())) {

				// Checks that the piece has not been captured this turn

				if (_board[p.getRank()][p.getFile()] == p) {
					toReturn.add(p);
				}
			}
		}
		return toReturn;
	}


}
