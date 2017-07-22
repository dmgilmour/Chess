import static java.lang.Math.abs;

public class Knight extends Piece {

	public Knight(int rank, int file, Player player, Board board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return "Knight";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		} else {
			return ((Math.abs(desiredRank) == 2 && Math.abs(desiredFile) == 1) || (Math.abs(desiredRank) == 1 && Math.abs(desiredFile) == 2));
		}
	}
}
