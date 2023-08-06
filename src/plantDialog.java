import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GUI for crop picking.
 */
public class plantDialog implements ActionListener{
    private JFrame frame = new JFrame ("MyFarm: Seed picker");
    private JPanel panel = new JPanel();
    private ArrayList <JButton> seedButtons = new ArrayList<JButton>();

    private JButton turnipButton = new JButton("Turnip");
    private JButton carrotButton = new JButton("Carrot");
    private JButton potatoButton = new JButton("Potato");
    private JButton roseButton = new JButton("Rose");
    private JButton tulipsButton = new JButton("Tulips");
    private JButton sunflowerButton = new JButton("Sunflower");
    private JButton mangoButton = new JButton("Mango");
    private JButton appleButton = new JButton("Apple");

    private myFarm myFarm;
    private tileGUI tileGUI;

    public plantDialog(myFarm myFarm, tileGUI tileGUI){
        this.myFarm = myFarm;
        this.tileGUI = tileGUI;
        frame.setVisible(true);
        frame.setSize(300,500);
        frame.setResizable(false);

        frame.add(panel);
        panel.setLayout(new GridLayout(8,1));
        initializeButtons();
        frame.setLocationRelativeTo(null);

    }

    public void initializeButtons(){
        seedButtons.add(turnipButton);
        seedButtons.add(carrotButton);
        seedButtons.add(potatoButton);
        seedButtons.add(roseButton);
        seedButtons.add(tulipsButton);
        seedButtons.add(sunflowerButton);
        seedButtons.add(mangoButton);
        seedButtons.add(appleButton);

        for (int i = 0; i < seedButtons.size(); i++){
            panel.add(seedButtons.get(i));
            seedButtons.get(i).setBackground(new Color(61, 76, 83));
            seedButtons.get(i).setForeground(Color.white);
            seedButtons.get(i).setFocusable(false);
            seedButtons.get(i).addActionListener(this);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == turnipButton){
            myFarm.plantSeed(myFarm.getInventory().getTurnip(), tileGUI);
        }
        if (e.getSource() == carrotButton){
            myFarm.plantSeed(myFarm.getInventory().getCarrot(), tileGUI);
        }
        if (e.getSource() == potatoButton){
            myFarm.plantSeed(myFarm.getInventory().getPotato(), tileGUI);
        }
        if (e.getSource() == roseButton){
            myFarm.plantSeed(myFarm.getInventory().getRose(), tileGUI);
        }
        if (e.getSource() == tulipsButton){
            myFarm.plantSeed(myFarm.getInventory().getTulips(), tileGUI);
        }
        if (e.getSource() == sunflowerButton){
            myFarm.plantSeed(myFarm.getInventory().getSunflower(), tileGUI);
        }
        if (e.getSource() == mangoButton){
            myFarm.plantSeed(myFarm.getInventory().getMango(), tileGUI);
        }
        if (e.getSource() == appleButton){
            myFarm.plantSeed(myFarm.getInventory().getApple(), tileGUI);
        }
        tileGUI.insertStats();
        tileGUI.displayTileStats();
        tileGUI.changeTileColor();
        frame.dispose();
    }
}
