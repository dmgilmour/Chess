import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainWindow extends JFrame {
	
	private final int HEIGHT = 1000;
	private final int WIDTH = 1000;

	private BoardPanel _boardPanel;
//	private TopPanel _topPanel;

	public MainWindow() {
		
		JFrame frame = new JFrame("Chess!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(WIDTH, HEIGHT);

		this.setLayout(new FlowLayout());

		_boardPanel = new BoardPanel();
		_boardPanel.setMaximumSize(new Dimension(800, 800));



//		_topPanel = new TopPanel(_boardPanel);
		
		this.add(_boardPanel);
//		this.add(_topPanel, BorderLayout.NORTH);
		this.setVisible(true);
	}
}

		
