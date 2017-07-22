
public class Pawn extends Piece {

	public Pawn(int rank, int file, Player player, Board board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return (_player.getNum() == 0 ? "White" : "Black") + " Pawn";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		}

		int direction = (_player.getNum() == 0 ? 1 : -1);
		if (desiredRank == _rank + direction) {
			if (desiredFile == _file) {
				return (_board.getBoard()[desiredRank][desiredFile].getPlayer() == null);
			} else if (Math.abs(desiredFile - _file) == 1) {
				return (_board.getBoard()[desiredRank][desiredFile].getPlayer() != null);
			}
		} else if (!_hasMoved && desiredRank == _rank + 2 * direction) {
			if (desiredFile == _file) {
				if (_board.getBoard()[desiredRank][desiredFile].getPlayer() == null) {
					if (_board.getBoard()[_rank + direction][_file].getPlayer() == null) {
						return true;
					}
				}
			}	
		}
		return false;
	}

		
}
