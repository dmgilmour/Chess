import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

public abstract class Piece {
	
	private Piece _this = this;
	protected int _rank;
	protected int _file;
	protected Player _player;
	protected Piece[][] _board;
	protected Logic _logic;

	protected boolean _hasMoved;

	private ClickListener _clickListener;
	
	public Piece(int rank, int file, Player player, Piece[][] board, Logic logic) {
		_rank = rank;
		_file = file;
		_player = player;
		_board = board;
		_logic = logic;
		_clickListener = new ClickListener();
		_hasMoved = false;
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

	public ImageIcon getImage() {
		return null;
	}

	public boolean canMove(int desiredRank, int desiredFile) {
		if (!boundsCheck(desiredRank, desiredFile)) {
			return false;
		}
		if (_board[desiredRank][desiredFile].getPlayer() == _player) {
			return false;
		} else {
			return true;
		}
	}

	public boolean move(int desiredRank, int desiredFile) {
		if (this.canMove(desiredRank, desiredFile)) {
			_board[_rank][_file] = new NullPiece(_rank, _file, _logic);
			this.setRank(desiredRank);
			this.setFile(desiredFile);
			_board[_rank][_file].remove();
			_board[_rank][_file] = this;
			_hasMoved = true;
			return true;
		} else { 
			return false;
		}
	}

	public ArrayList<Piece> getAvailableMoves() {
		return null;	
	}

	public ArrayList<Piece> getMovesToBlock(int rank, int file) {
		ArrayList<Piece> toReturn = new ArrayList<Piece>();
		toReturn.add(this);
		return toReturn;
	}

	public void remove() {
		_board[_rank][_file] = new NullPiece(_rank, _file, _logic);
		_player.getPieces().remove(this);
	}

	public boolean boundsCheck(int rank, int file) {
		return (rank >= 0 && rank < 8 && file >= 0 && file < 8);
	}

	public ActionListener getListener() {
		return _clickListener;
	}

	public boolean getHasMoved() {
		return _hasMoved;
	}

	class ClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("CL " + _this.getClass());
			_logic.registerClick(_this);
		}
	}
}
