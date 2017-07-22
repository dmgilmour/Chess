
public class Queen extends Piece {

	public Queen(int rank, int file, Player player, Board board) {
		super(rank, file, player, board);
	}

	@Override
	public String toString() {
		return "Queen";
	}
}
