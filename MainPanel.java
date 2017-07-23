import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;

public class MainPanel extends JPanel {
	
	private BoardPanel _boardPanel = null;
	private TopPanel _topPanel = null;

	public MainPanel() {
		
		this.setLayout(new BorderLayout());

		_boardPanel = new BoardPanel();

		_topPanel = new TopPanel(this);

		add(_topPanel, BorderLayout.NORTH);
		add(_boardPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void flipBoard() {
		_boardPanel.flipBoard();
	}
}
