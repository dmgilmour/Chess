import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;

public class BoardPanel extends JPanel {

	private static final int BLINK_TIME = 200;
	private static final int BLINK_COUNT = 3;

	private Piece[][] _board;
	private JPanel[][] _display;
	private JButton[][] _squares;
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


		_display = new JPanel[8][8];
		_squares = new JButton[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JButton sq = new JButton();
				sq.setPreferredSize(new Dimension(100, 100));
				sq.setMaximumSize(new Dimension(150, 150));
				if ((i + j) % 2 == 0) {
					sq.setBackground(new Color(0xf0d9b5));
				} else {
					sq.setBackground(new Color(0xb58863));
				}
				_squares[i][j] = sq;
				_display[i][j] = new JPanel();
				this.update(_board[i][j]);
				this.add(_display[i][j]);
			}
		}
		this.setLayout(new GridLayout(8, 8));
		display(_white);
	}


	
				
	public void display(Player player) {
		System.out.println("Displaying for " + player);	
		for (JPanel[] row : _display) {
			for (JPanel p : row) {
				if (p.getComponents() != null) {
					for (Component c : p.getComponents()) {
						p.remove(c);
					}
				}
			}
		}
		Player curPlayer = _logic.getCurPlayer();
		int orientation;
		int direction;
		if (curPlayer.getNum() == 0) {
			orientation = 7;
			direction = -1;
		} else {
			orientation = 0;
			direction = 1;
		}
		int displayY = 0;
		for (int squareY = orientation; squareY < 8 && squareY >= 0; squareY += direction) {
			int displayX = 0;
			for (int squareX = orientation; squareX < 8 && squareX >= 0; squareX += direction) {
				_display[displayY][displayX].add(_squares[squareY][squareX]);
				_display[displayY][displayX].update(_display[displayY][displayX].getGraphics());
				displayX++;	
			}
			displayY++;
		}
			
		this.setVisible(true);
	}


	public void update(Piece piece) {
		update(piece.getRank(), piece.getFile());
	}

	public void update(int rank, int file) {
		Piece piece = _board[rank][file];
		JButton sq = _squares[rank][file];
		for (ActionListener al : sq.getActionListeners()) {
			sq.removeActionListener(al);
		}
		
		sq.addActionListener(piece.getListener());
		ImageIcon image = new ImageIcon(getClass().getResource(("resources/" + piece.toString() + ".png")));
		image = new ImageIcon(image.getImage().getScaledInstance(110, 110,  java.awt.Image.SCALE_SMOOTH));
		sq.setIcon(image);
	}

	public void blinkSquare(Piece piece) {
		blinkSquare(piece.getRank(), piece.getFile());
	}

	public void blinkSquare(int rank, int file) {

		JButton sq = _squares[rank][file];
		Color prevColor = sq.getBackground();

		Timer blinkTimer = new Timer(BLINK_TIME, new ActionListener() {
			private int count = 0;
			private boolean active = false;

			public void actionPerformed(ActionEvent e) {
				if (count >= BLINK_COUNT) {
					sq.setBackground(prevColor);
					((Timer) e.getSource()).stop();
				} else {
					if (!active) {
						sq.setBackground(Color.RED);
						count++;
					} else {
						sq.setBackground(prevColor);
					}
					active = !active;
				}
			}
		});

		blinkTimer.start();
	}

	public void highlightSquare(Piece piece) {
		highlightSquare(piece.getRank(), piece.getFile());
	}

	public void highlightSquare(int rank, int file) {
		_squares[rank][file].setBackground(Color.YELLOW);
	}

	public void unhighlightSquare(Piece piece) {
		unhighlightSquare(piece.getRank(), piece.getFile());
	}

	public void unhighlightSquare(int rank, int file) {
		if ((rank + file) % 2 == 0) {
			_squares[rank][file].setBackground(new Color(0xf0d9b5));
		} else {
			_squares[rank][file].setBackground(new Color(0xb58863));
		}
	}
}
