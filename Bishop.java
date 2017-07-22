
public class Bishop extends Piece {

	public Bishop(int rank, int file, Player player, Board board) {
		super(rank, file, player, board);
	}

	@Override
	public String toString() {
		return "Bishop";
	}
}
