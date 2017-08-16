import java.util.ArrayList;

public class Pawn extends Piece {

	public Pawn(int rank, int file, Player player, Piece[][] board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	private void promote() {
		
		//prompt the user

		ArrayList<Piece> pieces = this.getPlayer().getPieces();
		pieces.add(new Queen(_rank, _file, _player, _board, _logic));
		this.remove();
		_board[_rank][_file] = pieces.get(pieces.size() - 1);
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
	public boolean move(int desiredRank, int desiredFile) {
		boolean success = super.move(desiredRank, desiredFile);

		if (success) {
			if (_rank == 0 || _rank == 7) {
				System.out.println("PROMOTE");
				this.promote();
			}
		}
		return success;
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

	@Override
	public ArrayList<Piece> getMovesToBlock(int rank, int file) {

		ArrayList<Piece> toReturn = new ArrayList<Piece>();
		toReturn.add(this);

		if (!canMove(rank, file)) {
			return null;
		}

		return toReturn;

	}
		
}
