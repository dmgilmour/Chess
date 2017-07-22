
public class King extends Piece {

	public King(int rank, int file, Player player, Board board) {
		super(rank, file, player, board);
	}

	@Override
	public String toString() {
		return "King";
	}
}
