
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class BoardPanel extends JPanel {

	private Board _board;
	private JButton[][] _display;
//	private GameLogic _logic;
	private Player _white;
	private Player _black;

	public BoardPanel() {

		_board = new Board(this);
		_white = new Player(0);
		_white.initializePieces(_board);
		_black = new Player(1);
		_black.initializePieces(_board);
		_board.setPlayerPieces(_white);
		_board.setPlayerPieces(_black);
//		_logic = new 
		_display = new JButton[8][8];
		initializeDisplay();
		display(_board);
	}

				
				
	private void initializeDisplay() {
		this.setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				_display[i][j] = new JButton();
				_display[i][j].setPreferredSize(new Dimension(100, 100));
				_display[i][j].setMaximumSize(new Dimension(150, 150));
				if ((i + j) % 2 == 0) {
					_display[i][j].setBackground(new Color(0xF5F5DC));
				} else {
					_display[i][j].setBackground(new Color(0x392613));
				}
				this.add(_display[i][j]);
			}
		}
	}

	private void display(Board board) {
		for (Piece[] row : board.getBoard()) {
			for (Piece p : row) {
				if (p != null) {
					_display[p.getRank()][p.getFile()].setText(p.toString());
				}
			}
		}
	}
}
