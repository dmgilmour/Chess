import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;

public class TopPanel extends JPanel {
	
	FlipButton _flipButton;
    ResignButton _resignButton;

	public TopPanel(MainPanel mainPanel) {
		
		_resignButton = new ResignButton(mainPanel);
		_flipButton = new FlipButton(mainPanel);

        add(_resignButton);
		add(_flipButton);
	}
}
