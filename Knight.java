import static java.lang.Math.abs;

public class Knight extends Piece {

	public Knight(int rank, int file, Player player, Board board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return (_player.getNum() == 0 ? "White" : "Black") + " Knight";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		} else {
			return ((Math.abs(desiredRank - _rank) == 2 && Math.abs(desiredFile - _file) == 1) || (Math.abs(desiredRank - _rank) == 1 && Math.abs(desiredFile - _file) == 2));
		}
	}
}
