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

		int rankDir = 0;
		int fileDir = 0;
		for (int i = 0; i < 9; i++) {
			rankDir = i % 3 - 1;
			fileDir = i / 3 - 1;
			if (rankDir == 0 && fileDir == 0) continue;
			int rankLoc = _rank + rankDir;
			int fileLoc = _file + fileDir;
			boolean hitPiece = false;
			while (boundsCheck(rankLoc, fileLoc) && !hitPiece) {
				if (_board[rankLoc][fileLoc].getPlayer() == null) {
					toReturn.add(_board[rankLoc][fileLoc]);
				} else if (_board[rankLoc][fileLoc].getPlayer() == _player) {
					hitPiece = true;
				} else {
					toReturn.add(_board[rankLoc][fileLoc]);
					hitPiece = true;
				}
				rankLoc += rankDir;
				fileLoc += fileDir;
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
