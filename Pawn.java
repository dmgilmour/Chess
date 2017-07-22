
public class Pawn extends Piece {

	public Pawn(int rank, int file, Player player, Board board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return (_player.getNum() == 0 ? "White" : "Black") + " Pawn";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		}
		if (desiredRank != (this.getRank() + (_player.getNum() == 0 ? 1 : -1))) {
			return false;
		} else if (desiredFile == this.getFile()) {
			return (_board.getBoard()[desiredRank][desiredFile].getPlayer() == null);
		} else {
			return (_board.getBoard()[desiredRank][desiredFile].getPlayer() != null);
		}
	}
}
