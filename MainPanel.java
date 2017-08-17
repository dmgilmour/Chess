import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;

public class MainPanel extends JPanel {
	
	private BoardPanel _boardPanel = null;
	private TopPanel _topPanel = null;
	private SidePanel _sidePanel = null;

	public MainPanel() {
		
		this.setLayout(new BorderLayout());

		_boardPanel = new BoardPanel(this);

		_topPanel = new TopPanel(this);

		_sidePanel = new SidePanel();

		add(_topPanel, BorderLayout.NORTH);
		add(_boardPanel, BorderLayout.CENTER);
		add(_sidePanel, BorderLayout.EAST);
		this.setVisible(true);
	}

	public void flipBoard() {
		_boardPanel.flipBoard();
	}

	public void printMove(String notation) {
		_sidePanel.printMove(notation);
	}
		
}
