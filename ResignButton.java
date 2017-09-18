import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ResignButton extends JButton {

    private MainPanel _mainPanel;

    public ResignButton(MainPanel mainPanel) {
        super("Reset");
        _mainPanel = mainPanel;
        addActionListener(new ResignListener());
    }

    class ResignListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            _mainPanel.resign();
        }
    }
}




