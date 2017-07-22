
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class BoardPanel extends JPanel {

	private Board _board;
	private JButton[][] _display;
	private Logic _logic;
	private Player _white;
	private Player _black;

	public BoardPanel() {

		_white = new Player(0);
		_black = new Player(1);
		_logic = new Logic(this, _white, _black);
		_board = new Board(this, _logic);

		_white.initializePieces(_board, _logic);
		_black.initializePieces(_board, _logic);
		_board.setPlayerPieces(_white);
		_board.setPlayerPieces(_black);

		_display = new JButton[8][8];
		initializeDisplay();
		display(_board);
		Piece temp = new NullPiece(0, 0, _logic);
		_logic.registerClick(temp);
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
				if (p instanceof NullPiece) System.out.println("Ayyo");
				_display[p.getRank()][p.getFile()].setText(p.toString());
				_display[p.getRank()][p.getFile()].addActionListener(p.getListener());
			}
		}
	}
}
