import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class BoardPanel extends JPanel {

	private Piece[][] _board;
	private JButton[][] _display;
	private Logic _logic;
	private Player _white;
	private Player _black;

	public BoardPanel() {

		_white = new Player("White", 0);
		_black = new Player("Black", 1);
		_logic = new Logic(this, _white, _black);
		_board = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				_board[i][j] = new NullPiece(i, j, _logic);
			}
		}

		_white.initializePieces(_board, _logic);
		_black.initializePieces(_board, _logic);


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
		for (int i = 7; i >= 0; i--) {
			for (int j = 7; j >= 0; j--) {
				update(_board[i][j], player);
				if ((i + j) % 2 == 0) {
					_display[i][j].setBackground(new Color(0xf0d9b5));
					_display[i][j].setForeground(Color.BLACK);
				} else {
					_display[i][j].setBackground(new Color(0xb58863));
					_display[i][j].setForeground(Color.WHITE);
				}
			}
		}
		this.setVisible(true);
	}

	public void update(Piece piece, Player player) {
		int rank;
		int file;
		if (player.getNum() == 0) {
			rank = piece.getRank();
			file = piece.getFile();
		} else {
			rank = 7 - piece.getRank();
			file = 7 - piece.getFile();
		}
			
		JButton sq = _display[rank][file];
		//sq.setText(piece.toString());
		for (ActionListener al : sq.getActionListeners()) {
			sq.removeActionListener(al);
		}
		
		sq.addActionListener(piece.getListener());
		sq.setPreferredSize(new Dimension(100, 100));
		sq.setMaximumSize(new Dimension(150, 150));
		ImageIcon image = new ImageIcon(getClass().getResource(("resources/" + piece.toString() + ".png")));
		image = new ImageIcon(image.getImage().getScaledInstance(110, 110,  java.awt.Image.SCALE_SMOOTH));
		sq.setIcon(image);
		this.add(sq);
	}

}
