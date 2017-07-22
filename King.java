import static java.lang.Math.abs;

public class King extends Piece {

	public King(int rank, int file, Player player, Board board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return "King";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		} else {
			return (Math.abs(desiredRank - _rank) <= 1 && Math.abs(desiredFile - _file) <= 1);
		}
	}
}
