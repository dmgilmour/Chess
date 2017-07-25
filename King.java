import static java.lang.Math.abs;
import java.util.ArrayList;

public class King extends Piece {

	public King(int rank, int file, Player player, Piece[][] board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return _player.toString() + "-King";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		} else if (!_hasMoved && desiredRank == _rank) {
			return canCastle(desiredRank, desiredFile);
		}

		return (Math.abs(desiredRank - _rank) <= 1 && Math.abs(desiredFile - _file) <= 1);
			
	}

	@Override
	public boolean move(int desiredRank, int desiredFile) {
		int rankDistance = desiredRank - _rank;
		int fileDistance = desiredRank - _file;
		if (rankDistance < 2 && rankDistance > -2 && fileDistance < 2 && fileDistance > -2) {
			return super.move(desiredRank, desiredFile);
		} else {
			return castle(desiredRank, desiredFile);
		}
	}	
		

	@Override
	public ArrayList<Piece> getAvailableMoves() {

		ArrayList<Piece> toReturn = new ArrayList<Piece>();

		int rLoc;
		int fLoc;
		for (int i = 0; i < 9; i++) {
			rLoc = _rank + i % 3 - 1;
			fLoc = _file + i / 3 - 1;
			if (rLoc == 0 && fLoc == 0) continue;
			if (boundsCheck(rLoc, fLoc)) {
				if (_board[rLoc][fLoc].getPlayer() != _player) {
					toReturn.add(_board[rLoc][fLoc]);
				}
			}
		}

		return toReturn;
	}

	private boolean canCastle(int desiredRank, int desiredFile) {

		// Might want to castle

		// Get direction of castle desired
		int direction = Integer.signum(desiredFile - _file);

		// Get which rook to castle with
		Piece rook = _board[_rank][(direction == 1 ? 7 : 0)];

		// Calculate the location (for 960)
		if (desiredFile == (rook.getFile() - (rook.getFile() - _file) / 2)) {

			if (!rook.getHasMoved()) {

				for (int loc = _file + direction; loc != rook.getFile(); loc += direction) {
					if (_board[_rank][loc].getPlayer() != null) {
						return false;
					}
				}

				if (_logic.inCheck(_player)) {
					return false;
				}

				for (int loc = _file + direction; loc != desiredFile + direction; loc += direction) {
					if (_logic.willCauseCheck(this, _board[_rank][loc])) {
						return false;
					}
				}

				return true;
			}
		}

		return false;

	}

	private boolean castle(int desiredRank, int desiredFile) {
		
		if (canCastle(desiredRank, desiredFile)) {

			// Get direction of castle desired
			int direction = Integer.signum(desiredFile - _file);

			// Get which rook to castle with
			Piece rook = _board[_rank][(direction == 1 ? 7 : 0)];

			int prevRank = rook.getRank();
			int prevFile = rook.getFile();

			rook.move(desiredRank, desiredFile - direction);

			_logic.updateSquare(prevRank, prevFile);
			_logic.updateSquare(rook.getRank(), rook.getFile());

			_board[_rank][_file] = new NullPiece(_rank, _file, _logic);
			_rank = desiredRank;
			_file = desiredFile;
			_board[_rank][_file] = this;
			_hasMoved = true;
			return true;

		} else {
			return false;
		}
	}
			
}
