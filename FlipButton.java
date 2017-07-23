import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FlipButton extends JButton {

	private MainPanel _mainPanel;

	public FlipButton(MainPanel mainPanel) {
		super("Flip Board");
		_mainPanel = mainPanel;
		addActionListener(new FlipListener());
	}

	class FlipListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			_mainPanel.flipBoard();
		}
	}
}


