
import java.awt.*;
import java.awt.event.*;
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
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				_display[i][j] = new JButton();
			}
		}
		this.setLayout(new GridLayout(8, 8));
		display(_white);
		Piece temp = new NullPiece(0, 0, _logic);
		_logic.registerClick(temp);
	}

				
	public void display(Player player) {
		
		for (int i = (player.getNum() == 0 ? 7 : 0); i < 8 && i >= 0; i += player.getNum() == 0 ? -1 : 1) {
			for (int j = (player.getNum() == 0 ? 7 : 0); j < 8 && j >= 0; j += player.getNum() == 0 ? -1 : 1) {
				Piece p = _board.getBoard()[i][j];
				JButton sq = _display[i][j];
				sq.setText(p.toString());
				for (ActionListener al : sq.getActionListeners()) {
					sq.removeActionListener(al);
				}
				
				sq.addActionListener(p.getListener());
				if ((i + j) % 2 == 0) {
					sq.setBackground(new Color(0xF5F5DC));
					sq.setForeground(Color.BLACK);
				} else {
					sq.setBackground(new Color(0x392613));
					sq.setForeground(Color.WHITE);
				}
				sq.setPreferredSize(new Dimension(100, 100));
				sq.setMaximumSize(new Dimension(150, 150));
				_display[i][j] = sq;
				this.add(sq);
			}
		}
		this.setVisible(true);
	}
}
