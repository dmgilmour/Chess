
public class Bishop extends Piece {

	public Bishop(int rank, int file, Player player, Board board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return "Bishop";
	}
}
