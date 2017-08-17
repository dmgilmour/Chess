import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;

public class SidePanel extends JPanel {

	JTextArea _ta;
	String _string;

	public SidePanel() {

		_ta  = new JTextArea();
		_string = "";
		this.add(_ta);
		this.setVisible(true);

	}

	public void printMove(String str) {
		_ta.append(str + "\n");
	}
}
