
public abstract class Piece {
	
	private int _rank;
	private int _file;
	protected Player _player;
	protected Board _board;
	
	public Piece(int rank, int file, Player player, Board board) {
		_rank = rank;
		_file = file;
		_player = player;
		_board = board;
	}

	public String toString() {
		return "A Piece";
	}

	public int getRank() {
		return _rank;
	}

	public int getFile() {
		return _file;
	}

	private void setRank(int rank) {
		_rank = rank;
	}

	private void setFile(int file) {
		_file = file;
	}

	public Player getPlayer() {
		return _player;
	}

	public boolean canMove(int desiredRank, int desiredFile) {
		if (!boundsCheck(desiredRank) || !boundsCheck(desiredFile)) {
			return false;
		}
		if (_board.getBoard()[desiredRank][desiredFile].getPlayer() == _player) {
			return false;
		} else {
			return true;
		}
	}

	public boolean move(int desiredRank, int desiredFile) {
		if (!canMove(desiredRank, desiredFile)) {
			return false;
		} else {
			this.setRank(desiredRank);
			this.setFile(desiredFile);
			return true;
		}
	}

	public boolean boundsCheck(int location) {
		return (location >= 0 && location < 8);
	}

		
}
