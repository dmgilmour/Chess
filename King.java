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
		} else {
			return (Math.abs(desiredRank - _rank) <= 1 && Math.abs(desiredFile - _file) <= 1);
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
}
