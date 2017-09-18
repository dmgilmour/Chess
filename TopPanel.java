import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;

public class TopPanel extends JPanel {
	
	public TopPanel(MainPanel mainPanel) {
		
		ResignButton resignButton = new ResignButton(mainPanel);
		FlipButton flipButton = new FlipButton(mainPanel);

        add(resignButton);
		add(flipButton);
	}
}
