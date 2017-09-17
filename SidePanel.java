import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import javax.swing.*;
import java.util.*;

public class SidePanel extends JPanel {

    JButton _copyButton;
	JTextArea _text;
	String _string;

	public SidePanel() {

        _copyButton = new JButton("Copy");
		_text  = new JTextArea();
		_string = "";
		this.add(_text);
        this.add(_copyButton);
		this.setVisible(true);

        _copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringSelection selection = new StringSelection(_text.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            }
        });
	}

	public void printMove(String str) {
		_text.append(str + "\n");
	}

    public void clearText() {
        _text.setText("");
    }
}
