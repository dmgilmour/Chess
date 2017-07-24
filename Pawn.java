import java.util.ArrayList;

public class Pawn extends Piece {

	public Pawn(int rank, int file, Player player, Piece[][] board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return _player.toString() + "-Pawn";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		}

		int direction = (_player.getNum() == 0 ? 1 : -1);
		if (desiredRank == _rank + direction) {
			if (desiredFile == _file) {
				return (_board[desiredRank][desiredFile].getPlayer() == null);
			} else if (Math.abs(desiredFile - _file) == 1) {
				return (_board[desiredRank][desiredFile].getPlayer() != null);
			}
		} else if (!_hasMoved && desiredRank == _rank + 2 * direction) {
			if (desiredFile == _file) {
				if (_board[desiredRank][desiredFile].getPlayer() == null) {
					if (_board[_rank + direction][_file].getPlayer() == null) {
						return true;
					}
				}
			}	
		}
		return false;
	}

	@Override
	public ArrayList<Piece> getAvailableMoves() {

		ArrayList<Piece> toReturn = new ArrayList<Piece>();

		int direction = (_player.getNum() == 0 ? 1 : -1);

		if (canMove(_rank + direction, _file)) {
			toReturn.add(_board[_rank + direction][_file]);
			if (canMove(_rank + 2*direction, _file)) {
				toReturn.add(_board[_rank + 2*direction][_file]); 
			}
		}

		if (canMove(_rank + direction, _file + 1)) {
			toReturn.add(_board[_rank + direction][_file + 1]); 
		}

		if (canMove(_rank + direction, _file - 1)) {
			toReturn.add(_board[_rank + direction][_file - 1]); 
		}

		return toReturn;
	}
}
