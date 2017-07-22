import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;

public class BoardPanel extends JPanel {

	private static final int TIMER_DELAY = 200;
	private static final int MAX_TIME = 1250;
	private static final String READY = "ready";

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
				} else {
					_display[i][j].setBackground(new Color(0xb58863));
				}
			}
		}
		this.setVisible(true);
	}

	public void update(Piece piece, Player curPlayer) {
		int rank;
		int file;
		if (curPlayer.getNum() == 0) {
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

	public void blinkPiece(Piece piece, Player curPlayer) {
		int rank;
		int file;
		if (curPlayer.getNum() == 0) {
			rank = piece.getRank();
			file = piece.getFile();
		} else {
			rank = 7 - piece.getRank();
			file = 7 - piece.getFile();
		}
		JButton sq = _display[rank][file];
		Color prevColor = sq.getBackground();
		Timer blinkTimer = new Timer(TIMER_DELAY, new ActionListener() {
			private int count = 0;
			private boolean on = false;

			public void actionPerformed(ActionEvent e) {
				if (count * TIMER_DELAY >= MAX_TIME) {
					sq.setBackground(prevColor);
					((Timer) e.getSource()).stop();
				} else {
					if (on) {
						sq.setBackground(Color.RED);
					} else {
						sq.setBackground(prevColor);
					}
					on = !on;
					count++;
				}
			}
		});
		blinkTimer.start();
	}

	public void highlightPiece(Piece piece, Player curPlayer) {
		int rank;
		int file;
		if (curPlayer.getNum() == 0) {
			rank = piece.getRank();
			file = piece.getFile();
		} else {
			rank = 7 - piece.getRank();
			file = 7 - piece.getFile();
		}
		_display[rank][file].setBackground(Color.YELLOW);
	}

	public void unhighlightPiece(Piece piece, Player curPlayer) {
		int rank;
		int file;
		if (curPlayer.getNum() == 0) {
			rank = piece.getRank();
			file = piece.getFile();
		} else {
			rank = 7 - piece.getRank();
			file = 7 - piece.getFile();
		}
		if ((rank + file) % 2 == 0) {
			_display[rank][file].setBackground(new Color(0xf0d9b5));
		} else {
			_display[rank][file].setBackground(new Color(0xb58863));
		}
	}

		
		
}
