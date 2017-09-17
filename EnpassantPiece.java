
public class EnpassantPiece extends Piece {

	private Piece _referencedPawn;

	public EnpassantPiece(int rank, int file, Logic logic, Piece referencedPawn) {
		super(rank, file, null, null, logic);
		_referencedPawn = referencedPawn;
	}

	@Override
	public String toString() {
		return "Null";
	}

	@Override
	public void remove() {
		int rank = _referencedPawn.getRank();
		int file = _referencedPawn.getFile();

		_referencedPawn.remove();

		_logic.updateSquare(rank, file);
	}

    public Player enpassantOwner() {
        return _referencedPawn.getPlayer();
    }
		
}
