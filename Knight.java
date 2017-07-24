import static java.lang.Math.abs;
import java.util.ArrayList;

public class Knight extends Piece {

	public Knight(int rank, int file, Player player, Piece[][] board, Logic logic) {
		super(rank, file, player, board, logic);
	}

	@Override
	public String toString() {
		return _player.toString() + "-Knight";
	}

	@Override
	public boolean canMove(int desiredRank, int desiredFile) {
		if (!super.canMove(desiredRank, desiredFile)) {
			return false;
		} else {
			return ((Math.abs(desiredRank - _rank) == 2 && Math.abs(desiredFile - _file) == 1) || (Math.abs(desiredRank - _rank) == 1 && Math.abs(desiredFile - _file) == 2));
		}
	}

	@Override
	public ArrayList<Piece> getAvailableMoves() {

		ArrayList<Piece> toReturn = new ArrayList<Piece>();
		
		if (canMove(_rank + 2, _file + 1)) {
			toReturn.add(_board[_rank + 2][_file + 1]);
		}
		if (canMove(_rank + 2, _file - 1)) {
			toReturn.add(_board[_rank + 2][_file - 1]);
		}
		if (canMove(_rank - 2, _file + 1)) {
			toReturn.add(_board[_rank - 2][_file + 1]);
		}
		if (canMove(_rank - 2, _file - 1)) {
			toReturn.add(_board[_rank - 2][_file - 1]);
		}
		if (canMove(_rank + 1, _file + 2)) {
			toReturn.add(_board[_rank + 1][_file + 2]);
		}
		if (canMove(_rank + 1, _file - 2)) {
			toReturn.add(_board[_rank + 1][_file - 2]);
		}
		if (canMove(_rank - 1, _file + 2)) {
			toReturn.add(_board[_rank - 1][_file + 2]);
		}
		if (canMove(_rank - 1, _file - 2)) {
			toReturn.add(_board[_rank - 1][_file - 2]);
		}
		
		return toReturn;
	}
}
