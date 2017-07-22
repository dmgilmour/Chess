
public class Rook extends Piece {

	public Rook(int rank, int file, Player player, Board board) {
		super(rank, file, player, board);
	}

	@Override
	public String toString() {
		return "Rook";
	}
}
