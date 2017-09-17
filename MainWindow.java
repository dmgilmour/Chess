import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainWindow extends JFrame {
	
	private final int HEIGHT = 1000;
	private final int WIDTH = 1000;

	private MainPanel _mainPanel;
//	private TopPanel _topPanel;

	public MainWindow() {
		
		JFrame frame = new JFrame("Chess!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(WIDTH, HEIGHT);

		this.setLayout(new FlowLayout());

		_mainPanel = new MainPanel(this);
		_mainPanel.setMaximumSize(new Dimension(800, 800));



//		_topPanel = new TopPanel(_boardPanel);
		
		this.add(_mainPanel);
//		this.add(_topPanel, BorderLayout.NORTH);
		this.setVisible(true);
	}
}

		
