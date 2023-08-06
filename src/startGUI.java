import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for user name input.
 */
public class startGUI implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JTextField input = new JTextField();
    JButton button = new JButton();
    myFarm myFarm;

    public startGUI(myFarm farm){
        myFarm = farm;
        frame.setSize(400,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("MyFarm");

        frame.add(panel);
        panel.add(label);
        panel.setSize(200,200);

        label.setText("Enter name: ");

        panel.add(input);
        input.setPreferredSize(new Dimension(100,30));
        input.setVisible(true);

        panel.add(button);
        button.setPreferredSize(new Dimension(90,30));
        button.setText("Play!");
        button.setBackground(new Color(77, 179, 179));
        button.setForeground(Color.white);
        button.addActionListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            myFarm.setPlayer(new PlayerInfo(input.getText()));
            frame.dispose();
            new tileGUI(myFarm);
        }
    }
}
