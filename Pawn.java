import java.util.ArrayList;

public class Pawn extends Piece {

	public Pawn(int rank, int file, Player player, Piece[][] board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	private void promote() {
		
		this.remove();
		PromotionHandler.getDesiredPiece(_rank, _file, _player, _board, _logic);
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
		Piece destination = _board[desiredRank][desiredFile];
		if (desiredRank == _rank + direction) {
			if (desiredFile == _file) {
				return (destination.getPlayer() == null);
			} else if (Math.abs(desiredFile - _file) == 1) {
				return ((destination.getPlayer() != _player) && (destination.getPlayer() != null || (destination instanceof EnpassantPiece && ((EnpassantPiece) destination).enpassantOwner() != _player)));
			}
		} else if (!_hasMoved && desiredRank == _rank + 2 * direction) {
			if (desiredFile == _file) {
				if (destination.getPlayer() == null) {
					if (destination.getPlayer() == null) {
                        System.out.println("ayyo");
                        System.out.println(_rank + " " + _file);
						return true;
					}
				}
			}	
		}
		return false;
	}

	@Override
	public boolean move(int desiredRank, int desiredFile) {

		boolean makesEnpassant = false;
		int direction = (_player.getNum() == 0 ? 1 : -1);
		if (desiredRank == _rank + 2 * direction) {
			makesEnpassant = true;
		}
			

		boolean success = super.move(desiredRank, desiredFile);


		if (success) {
			if (makesEnpassant) {
				_board[_rank - direction][_file] = new EnpassantPiece(_rank - direction, _file, _board, _logic, this);
				_logic.updateSquare(_rank - direction, _file);
			}
			if (_rank == 0 || _rank == 7) {
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
		}

        if (canMove(_rank + 2*direction, _file)) {
            toReturn.add(_board[_rank + 2*direction][_file]); 
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
