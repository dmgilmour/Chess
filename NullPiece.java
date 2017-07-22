
public class NullPiece extends Piece {

	public NullPiece(int rank, int file, Logic logic) {
		super(rank, file, null, null, logic);
	}

	@Override
	public String toString() {
		return "";
	}
}
