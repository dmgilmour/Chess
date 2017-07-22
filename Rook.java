
public class Rook extends Piece {

	public Rook(int rank, int file, Player player, Board board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return "Rook";
	}
}
