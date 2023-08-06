import body.Farmer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GUI for farm upgrade choosing.
 */
public class farmDialog implements ActionListener {

    private JFrame frame = new JFrame ("MyFarm: Farm type upgrade");
    private JPanel panel = new JPanel();
    private ArrayList<JButton> farmLevelButtons = new ArrayList<JButton>();

    private JButton defaultButton = new JButton("Default Farmer");
    private JButton registeredButton = new JButton("Registered Farmer");
    private JButton distinguishedButton = new JButton("Distinguished Farmer");
    private JButton legendaryButton = new JButton("Legendary Farmer");

    private myFarm myFarm;
    private tileGUI tileGUI;

    public farmDialog(myFarm myFarm, tileGUI tileGUI){
        this.myFarm = myFarm;
        this.tileGUI = tileGUI;
        frame.setVisible(true);
        frame.setSize(300,500);
        frame.setResizable(false);

        frame.add(panel);
        panel.setLayout(new GridLayout(4,1));
        initializeButtons();
        frame.setLocationRelativeTo(null);

    }

    public void initializeButtons(){
        farmLevelButtons.add(defaultButton);
        farmLevelButtons.add(registeredButton);
        farmLevelButtons.add(distinguishedButton);
        farmLevelButtons.add(legendaryButton);

        for (int i = 0; i < farmLevelButtons.size(); i++){
            panel.add(farmLevelButtons.get(i));
            farmLevelButtons.get(i).setBackground(new Color(61, 76, 83));
            farmLevelButtons.get(i).setForeground(Color.white);
            farmLevelButtons.get(i).setFocusable(false);
            farmLevelButtons.get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == defaultButton){
            myFarm.farmerUpgrade(new DefaultFarmer(), tileGUI);
        }
        if (e.getSource() == registeredButton){
            myFarm.farmerUpgrade(new RegisteredFarmer(), tileGUI);
        }
        if (e.getSource() == distinguishedButton){
            myFarm.farmerUpgrade(new DistinguishedFarmer(), tileGUI);
        }
        if (e.getSource() == legendaryButton){
            myFarm.farmerUpgrade(new LegendaryFarmer(), tileGUI);
        }
        tileGUI.insertStats();
        frame.dispose();
    }
}
