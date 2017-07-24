import static java.lang.Math.abs;
import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(int rank, int file, Player player, Piece[][] board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return _player.toString() + "-Queen";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		} else if (desiredRank - _rank == 0 || desiredFile - _file == 0 || Math.abs(desiredRank - _rank) == Math.abs(desiredFile - _file)) {
			int rankDir = Integer.signum(desiredRank - _rank);
			int fileDir = Integer.signum(desiredFile - _file);

			int r = _rank + rankDir;
			int f = _file + fileDir;

			while (r != desiredRank || f != desiredFile) {
				if (_board[r][f].getPlayer() != null) {
					return false;
				}
				r += rankDir;
				f += fileDir;
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
		for (int i = 0; i < 9; i++) {
			rDir = i % 3 - 1;
			fDir = i / 3 - 1;
			if (rDir == 0 && fDir == 0) continue;
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
}
