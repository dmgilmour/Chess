
public class EnpassantPiece extends Piece {

	public EnpassantPiece(int rank, int file, Player player, Piece[][] board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFil) {
		return false;
	}
}
