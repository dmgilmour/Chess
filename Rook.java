
public class Rook extends Piece {

	public Rook(int rank, int file, Player player, Board board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return "Rook";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		} else if ((desiredRank - _rank) == 0 || (desiredFile - _file) == 0) {
			int rankDir = Integer.signum(desiredRank - _rank);
			int fileDir = Integer.signum(desiredFile - _file);

			int r = _rank + rankDir;
			int f = _file + fileDir;

			while (r != desiredRank || f != desiredFile) {
				if (_board.getBoard()[r][f].getPlayer() != null) {
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
}
