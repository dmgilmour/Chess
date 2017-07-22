import java.awt.event.*;

public abstract class Piece {
	
	private Piece _this = this;
	private int _rank;
	private int _file;
	protected Player _player;
	protected Board _board;
	protected Logic _logic;

	private ClickListener _clickListener;
	
	public Piece(int rank, int file, Player player, Board board, Logic logic) {
		_rank = rank;
		_file = file;
		_player = player;
		_board = board;
		_logic = logic;
		_clickListener = new ClickListener();
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
		if (canMove(desiredRank, desiredFile)) {
			this.setRank(desiredRank);
			this.setFile(desiredFile);
			return true;
		} else {
			return false;
		}
	}

	public boolean boundsCheck(int location) {
		return (location >= 0 && location < 8);
	}

	public ActionListener getListener() {
		return _clickListener;
	}

	public Piece get() {
		return _this;
	}

	class ClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			_logic.registerClick(_this);
		}
	}
}
