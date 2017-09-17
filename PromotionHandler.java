import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PromotionHandler {


    private static JDialog promotionDialog;
    private static MainWindow _window;
    private static int BUTTON_SIZE = 80;

    public PromotionHandler(MainPanel mainPanel) {
        _window = mainPanel.getWindow();
    }

    public static void getDesiredPiece(int rank, int file, Player player, Piece[][] board, Logic logic) {

        System.out.println("YOYOYO");

        promotionDialog = new JDialog(_window, "Promote Pawn", true);
        
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel promotionPanel = new JPanel();
        promotionPanel.setLayout(new GridBagLayout());
        ImageIcon image;

        JButton queenButton = new JButton();
        image = new ImageIcon(PromotionHandler.class.getResource(("resources/" + player + "-Queen.png")));
        image = new ImageIcon(image.getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH));
        queenButton.setIcon(image);
        queenButton.setBackground(Color.WHITE);

        queenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPiece(player, board, new Queen(rank, file, player, board, logic));
                promotionDialog.dispose();
            }
        });

        constraints.gridy++;
        promotionPanel.add(queenButton, constraints);

        JButton rookButton = new JButton();
        image = new ImageIcon(PromotionHandler.class.getResource(("resources/" + player + "-Rook.png")));
        image = new ImageIcon(image.getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH));
        rookButton.setIcon(image);
        rookButton.setBackground(Color.WHITE);

        rookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPiece(player, board, new Rook(rank, file, player, board, logic));
                promotionDialog.dispose();
            }
        });

        constraints.gridy++;
        promotionPanel.add(rookButton, constraints);

        JButton bishopButton = new JButton();
        image = new ImageIcon(PromotionHandler.class.getResource(("resources/" + player + "-Bishop.png")));
        image = new ImageIcon(image.getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH));
        bishopButton.setIcon(image);
        bishopButton.setBackground(Color.WHITE);

        bishopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPiece(player, board, new Bishop(rank, file, player, board, logic));
                promotionDialog.dispose();
            }
        });


        constraints.gridy++;
        promotionPanel.add(bishopButton, constraints);

        JButton knightButton = new JButton();
        image = new ImageIcon(PromotionHandler.class.getResource(("resources/" + player + "-Knight.png")));
        image = new ImageIcon(image.getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH));
        knightButton.setIcon(image);
        knightButton.setBackground(Color.WHITE);

        knightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPiece(player, board, new Knight(rank, file, player, board, logic));
                promotionDialog.dispose();
            }
        });

        constraints.gridy++;
        promotionPanel.add(knightButton, constraints);

        promotionDialog.getContentPane().add(promotionPanel);
        promotionDialog.pack();
        promotionDialog.setVisible(true);

    }

    private static void addPiece(Player player, Piece[][] board, Piece piece) {

        System.out.println(piece);
        player.getPieces().add(piece);
        board[piece.getRank()][piece.getFile()] = piece;

    }

}
        




        
