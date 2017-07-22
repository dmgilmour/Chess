
public class Pawn extends Piece {

	public Pawn(int rank, int file, Player player, Board board) {
		super(rank, file, player, board);
	}

	@Override
	public String toString() {
		return "Pawn";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!boundsCheck(desiredRank) || !boundsCheck(desiredFile)) {
			return false;
		} else {
			if (desiredRank != (this.getRank() + (_player.getNum() == 1 ? 1 : -1))) {
				return false;
			} else if (desiredFile == this.getFile()) {
				return (_board.getBoard()[desiredRank][desiredFile] == null);
			} else {
				return (_board.getBoard()[desiredRank][desiredFile].getPlayer() != _player);
			}
		}
	}
}
