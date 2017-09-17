import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;

public class BoardPanel extends JPanel {

	private static final int BLINK_TIME = 200;
	private static final int BLINK_COUNT = 3;

    private static final int BUTTON_SIZE = 90;

	private static final Color LIGHT_SQUARE_COLOR = new Color(0xf0d9b5);
	private static final Color DARK_SQUARE_COLOR = new Color(0xb58863);

	public MainPanel _mainPanel;

	private Piece[][] _board;
	private JPanel[][] _display;
	private JButton[][] _squares;
	private Logic _logic;
	private boolean _inTrueOrientation = true;

    Timer blinkTimer;

    public static PromotionHandler promotionHandler;

	public BoardPanel(MainPanel mainPanel) {

		_mainPanel = mainPanel;

        promotionHandler = new PromotionHandler(_mainPanel);

		this.setLayout(new GridLayout(8, 8));

		_squares = makeSquares();
		_display = makeDisplay();

        newGame();
	}

    public void newGame() {

		Player white = new Player("White", 0);
		Player black = new Player("Black", 1);
		_board = new Piece[8][8];
		_logic = new Logic(this, white, black, _board);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				_board[i][j] = new NullPiece(i, j, _logic);
			}
		}
		white.initializePieces(_board, _logic);
		black.initializePieces(_board, _logic);
		for (Piece[] row : _board) {
			for (Piece p : row) {
				this.update(p);
			}
		}

		display(white);
    }


	private JButton[][] makeSquares() {
		JButton[][] squares = new JButton[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JButton sq = new JButton();
				sq.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
				if ((i + j) % 2 == 0) {
					sq.setBackground(DARK_SQUARE_COLOR);
				} else {
					sq.setBackground(LIGHT_SQUARE_COLOR);
				}
				squares[i][j] = sq;
			}
		}
		return squares;
	}

	private JPanel[][] makeDisplay() {
		JPanel[][] display = new JPanel[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				display[i][j] = new JPanel();
				this.add(display[i][j]);
			}
		}
		return display;
	}
				


	
				
	public void display(Player player) {

		// Remove the previous button
		for (JPanel[] row : _display) {
			for (JPanel p : row) {
				if (p.getComponents() != null) {
					for (Component c : p.getComponents()) {
						p.remove(c);
					}
				}
			}
		}

		// Get which orientation we need to display the board in
		int orientationX;
		int orientationY;
		int directionX;
		int directionY;
		if (player.getNum() == 0) {
			orientationX = 0;
			orientationY = 7;
			directionX = 1;
			directionY = -1;
		} else {
			orientationX = 7;
			orientationY = 0;
			directionX = -1;
			directionY = 1;
		}

		// Assign the squares to the display in the given players orientation 
		int displayY = 0;
		for (int squareY = orientationY; squareY < 8 && squareY >= 0; squareY += directionY) {
			int displayX = 0;
			for (int squareX = orientationX; squareX < 8 && squareX >= 0; squareX += directionX) {
				_display[displayY][displayX].add(_squares[squareY][squareX]);
				_display[displayY][displayX].update(_display[displayY][displayX].getGraphics());
				displayX++;	
			}
			displayY++;
		}

		_inTrueOrientation = (player == _logic.getCurPlayer());

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
		image = new ImageIcon(image.getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE,  java.awt.Image.SCALE_SMOOTH));
		sq.setIcon(image);
	}

	public void blinkSquare(Piece piece) {
		blinkSquare(piece.getRank(), piece.getFile());
	}

	public void blinkSquare(int rank, int file) {

		JButton sq = _squares[rank][file];
		Color prevColor = sq.getBackground();

		blinkTimer = new Timer(BLINK_TIME, new ActionListener() {
			private int count = 0;
			public boolean active = false;

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
        if (blinkTimer != null) {
            blinkTimer.stop();
        }
		if ((rank + file) % 2 == 0) {
			_squares[rank][file].setBackground(DARK_SQUARE_COLOR);
		} else {
			_squares[rank][file].setBackground(LIGHT_SQUARE_COLOR);
		}
	}
	
	public void flipBoard() {
		if (_inTrueOrientation) {
			display(_logic.getOpponent());
			_inTrueOrientation = false;
		} else {
			display(_logic.getCurPlayer());
			_inTrueOrientation = true;
		}
	}

	public void printMove(String notation) {
		_mainPanel.printMove(notation);
	}

}
