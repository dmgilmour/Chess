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

			makeSelection(p);

		} else if (p == _pieceToMove) {

			clearSelection();

		} else if (_selectionBeenMade && p.getPlayer() == _curPlayer) {

			// Second select and selected another piece they own, changing selected piece 

			clearSelection();
			makeSelection(p);

		} else if (_pieceToMove.canMove(p.getRank(), p.getFile())) { 

			// Second select and chosen a valid square, checking if causes check

			int prevRank = _pieceToMove.getRank();
			int prevFile = _pieceToMove.getFile();
			
			if (!willCauseCheck(_pieceToMove, p)) {

				// Does not cause check, moving piece

				String notation = createNotation(_pieceToMove, p);

				int nextRank = p.getRank();
				int nextFile = p.getFile();
				_pieceToMove.move(nextRank, nextFile);
				p.remove();

				_boardPanel.update(nextRank, nextFile);
				_boardPanel.update(prevRank, prevFile);
				_boardPanel.unhighlightSquare(prevRank, prevFile);

				_pieceToMove = null;
				_selectionBeenMade = false;
				nextTurn();
			} else {

				// Causes check, displaying error

				for (Piece c : getPiecesChecking(_curPlayer)) {
					_boardPanel.blinkSquare(c);
				}

				_boardPanel.blinkSquare(_curPlayer.getKing());
			}

		} else {

			// Piece unable to move to square

			_boardPanel.blinkSquare(_pieceToMove);

		}
	}

	public String createNotation(Piece start, Piece finish) {

		ArrayList<Piece> pieces = start.getPlayer().getPieces();
	
		String toReturn = "";

		if (start instanceof Pawn) {
			if (!(finish instanceof NullPiece)) {
				toReturn += (char) (start.getFile() + 97);
			}
		} else if (start instanceof Knight) {
			toReturn = "N";
		} else if (start instanceof Bishop) {
			toReturn = "B";
		} else if (start instanceof Rook) {
			toReturn = "R";
		} else if (start instanceof Queen) {
			toReturn = "Q";
		} else if (start instanceof King) {
			toReturn = "K";
		} else {
			return null;
		}

		boolean ambiguous = false;
		boolean sharedFile = false;
		boolean sharedRank = false;

		if (!(start instanceof Pawn)) {
			for (Piece p : pieces) {
				if (p.getClass().equals(start.getClass()) && p != start) {
					if (p.canMove(finish.getRank(), finish.getFile())) {
						ambiguous = true;
						if (p.getRank() == start.getRank()) {
							sharedRank = true;
						}
						if (p.getFile() == start.getFile()) {
							sharedFile = true;
						}
					}
				}
			}
		}

		if (ambiguous) {
			if (!sharedFile) {
				toReturn += (char) (start.getFile() + 97);
			} else if (!sharedRank) {
				toReturn += (start.getRank() + 1);
			} else {
				toReturn += (char) (start.getFile() + 97);
				toReturn += (start.getRank() + 1);
			}
		}
				
		if (!(finish instanceof NullPiece)) {
			toReturn += "x";
		}
		toReturn += (char) (finish.getFile() + 97);
		toReturn += (finish.getRank() + 1);

		

		if (isCheckmate(_opponent)) {
			toReturn += "#";
		} else if (inCheck(_opponent)) {
			toReturn += "+";
		}

		System.out.println(toReturn);
		return toReturn;
	}

	public void updateSquare(int rank, int file) {
		_boardPanel.update(rank, file);
	}

	private void nextTurn() {

		// Check for mate
		System.out.println("Checkmate for opponent: " + isCheckmate(_opponent));
		Player tempPlayer = _curPlayer;
		_curPlayer = _opponent;
		_opponent = tempPlayer;
		_boardPanel.display(_curPlayer);

	}

	public boolean inCheck(Player player) {
		return inCheck(player, player.getKing().getRank(), player.getKing().getFile());
	}

	public boolean inCheck(Player player, int rank, int file) {

		// inCheck can be called on any player so we check the opponent
		// relative to 
		Player opponent = (player == _curPlayer ? _opponent : _curPlayer);

		for (Piece p : opponent.getPieces()) {
			if (p.canMove(rank, file)) {

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

		boolean toReturn;

		if (toMove == toMove.getPlayer().getKing()) {
			toReturn = inCheck(toMove.getPlayer(), nextRank, nextFile);	
		} else {
			
			toReturn = inCheck(toMove.getPlayer());
		}

		_board[prevRank][prevFile] = toMove;
		_board[nextRank][nextFile] = toTake;

		return toReturn;
	}

	private boolean isCheckmate(Player player) {
		
		if (!inCheck(player)) {
			return false;
		} else {

			ArrayList<Piece> attackingPieces = getPiecesChecking(player);

			Piece king = player.getKing();

			// Try moving the king
			for (Piece sq : king.getAvailableMoves()) {
				if (!willCauseCheck(king, sq)) {
					System.out.println("King can move");
					return false;
				}
			}

			if (attackingPieces.size() > 1) {

				return true;

			} else {

				Piece attacker = attackingPieces.get(0);
				for (Piece blockingSquare : attacker.getMovesToBlock(king.getRank(), king.getFile())) {
					System.out.println(blockingSquare.getRank() + " : " + blockingSquare.getFile());
					System.out.println(blockingSquare);
					for (Piece defender : player.getPieces()) {
						System.out.println(defender);
						System.out.println(defender.canMove(blockingSquare.getRank(), blockingSquare.getFile()));
						if (defender.canMove(blockingSquare.getRank(), blockingSquare.getFile())) {
							if (!willCauseCheck(defender, blockingSquare)) {
								return false;
							}
						}
					}
				}
			}

		}
		return true;
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

	private void makeSelection(Piece selected) {
		_selectionBeenMade = true;
		_pieceToMove = selected;
		_boardPanel.highlightSquare(_pieceToMove);
	}

	private void clearSelection() {
		_boardPanel.unhighlightSquare(_pieceToMove);
		_selectionBeenMade = false;
		_pieceToMove = null;
	}


}
