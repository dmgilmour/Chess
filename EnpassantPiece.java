
public class EnpassantPiece extends Piece {

	private Piece _referencedPawn;

	public EnpassantPiece(int rank, int file, Piece[][] board, Logic logic, Piece referencedPawn) {
		super(rank, file, null, board, logic);
		_referencedPawn = referencedPawn;
	}

	@Override
	public String toString() {
		return "Null";
	}

	@Override
	public void remove() {

		if (_board[_rank][_file] instanceof Pawn) {
			int rank = _referencedPawn.getRank();
			int file = _referencedPawn.getFile();
			System.out.println("HEY RITE HERE");
			_referencedPawn.remove();
			_logic.updateSquare(rank, file);
		} else if (_board[_rank][_file] instanceof EnpassantPiece) {
			System.out.println("ITS ENPASSANT");
		} else {
			System.out.println("IT IS NOT");
		}


	}

    public Player enpassantOwner() {
        return _referencedPawn.getPlayer();
    }
		
}
