import javax.swing.*;
import java.awt.*;

/**
 * GUI for ending the game.
 */
public class gameOverDialog {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel("Game over.");

    public gameOverDialog (){
        frame.setSize(200,100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("MyFarm: Game Over");
        frame.add(panel);
        panel.setBackground(Color.red);
        panel.add(label);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }
}
