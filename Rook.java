import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(int rank, int file, Player player, Piece[][] board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return _player.toString() + "-Rook";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		} else if ((desiredRank - _rank) == 0 || (desiredFile - _file) == 0) {
			int rankDir = Integer.signum(desiredRank - _rank);
			int fileDir = Integer.signum(desiredFile - _file);

			int rankLoc = _rank + rankDir;
			int fileLoc = _file + fileDir;

			while (rankLoc != desiredRank || fileLoc != desiredFile) {
				if (_board[rankLoc][fileLoc].getPlayer() != null) {
					return false;
				}
				rankLoc += rankDir;
				fileLoc += fileDir;
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ArrayList<Piece> getAvailableMoves() {

		ArrayList<Piece> toReturn = new ArrayList<Piece>();

		int rDir = 0;
		int fDir = 0;
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				rDir = 1;
				fDir = 0;
			} else if (i == 2) {
				rDir = -1;
				fDir = 0;
			} else if (i == 3) {
				rDir = 0;
				fDir = 1;
			} else {
				rDir = 0;
				fDir = -1;
			}
			int rLoc = _rank + rDir;
			int fLoc = _file + fDir;
			boolean hitPiece = false;
			while (boundsCheck(rLoc, fLoc) && !hitPiece) {
				if (_board[rLoc][fLoc].getPlayer() == null) {
					toReturn.add(_board[rLoc][fLoc]);
				} else if (_board[rLoc][fLoc].getPlayer() == _player) {
					hitPiece = true;
				} else {
					toReturn.add(_board[rLoc][fLoc]);
					hitPiece = true;
				}
				rLoc += rDir;
				fLoc += fDir;
			}
		}

		return toReturn;
	}

	@Override
	public ArrayList<Piece> getMovesToBlock(int rank, int file) {

		ArrayList<Piece> toReturn = new ArrayList<Piece>();
		toReturn.add(this);

		if (!canMove(rank, file)) {
			return null;
		}

		int rankDir = Integer.signum(rank - _rank);
		int fileDir = Integer.signum(file - _file);

		int rankLoc = _rank + rankDir;
		int fileLoc = _file + fileDir;

		while (rankLoc != rank && fileLoc != file) {
			toReturn.add(_board[rankLoc][fileLoc]);
			rankLoc += rankDir;
			fileLoc += fileDir;
		}

		return toReturn;
	}
}
