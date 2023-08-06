import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import body.FarmTools.*;
import body.Farmer.*;
import body.Crops.*;

/**
 * Tile GUI
 */
public class tileGUI implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JPanel tilePanel = new JPanel();
    private JPanel tool = new JPanel();
    private JPanel actionPanel = new JPanel();
    private JTextArea textPane = new JTextArea();
    private JScrollPane textPanelSP = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JLabel stats = new JLabel();

    private myFarm myFarm;

    private ArrayList<JButton> toolButton = new ArrayList<JButton>();
    private JButton waterButton = new JButton("Water");
    private JButton plantButton = new JButton("Plant");
    private JButton plowButton = new JButton("Plow");
    private JButton fertilizeButton = new JButton("Fertilizer");
    private JButton pickaxeButton = new JButton("Pickaxe");
    private JButton shovelButton= new JButton("Shovel");
    private JButton harvestButton = new JButton("Harvest");
    private JButton nextDay = new JButton("Next day");
    private JButton upgradeButton = new JButton("Upgrade");


    private ArrayList<JButton> tilesButtons = new ArrayList<JButton>();

    private JTextArea tileStatus = new JTextArea();
    private JTextArea tileHarvestStatus = new JTextArea();


    public tileGUI (myFarm farm){
        this.myFarm = farm;
        frame.setSize(1000,800);
        frame.setVisible(true);
        frame.setTitle("MyFarm");
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        insertTextPane();

        frame.add(panel);
        panel.setBounds(0,600,250,200);
        panel.setBackground(new Color(77, 179, 179));
        panel.setLayout(null);

        stats.setBounds(10,0,230,180);
        panel.add(stats);
        insertStats();

        frame.add(tool);
        tool.setBounds(242,600,750,40);
        tool.setBackground(new Color( 230, 119, 46));
        insertActionButtons();

        insertTileStatusPane();
        insertTileHarvestStatusPane();

        frame.add(tilePanel);
        tilePanel.setBounds(252,0,730,600);
        tilePanel.setLayout(new GridLayout(5,10));
        tilePanel.setBackground(new Color(242, 194, 73));
        setTiles();

        myFarm.readRockFile();
        displayTileStats();
        myFarm.gameStateChecker(this);
        changeTileColor();
    }

    /**
     * Instantiates the tiles in myFarm and in the JButton tile button array.
     */
    public void setTiles(){
        for (int i = 0; i < 50; i++){
            tilesButtons.add(new JButton());
            tilePanel.add(tilesButtons.get(i));
            tilesButtons.get(i).setFocusable(false);
            tilesButtons.get(i).setText(""+(i + 1));
            tilesButtons.get(i).addActionListener(this);
            myFarm.getTiles().add(new Tile(i, this, myFarm));
        }
    }

    /**
     * Gets players stats and displays in label.
     */
    public void insertStats(){
        myFarm.getPlayer().getLevelCompute();
        stats.setText("<html>[Day " + myFarm.getDay() + "]<br/><br/" +
                "Name: " + myFarm.getPlayer().getName() + "<br/>" +
                "ObjectCoins: " + Math.round(myFarm.getPlayer().getObjectCoins()) + "C<br/>" +
                "XP: " + Math.round(myFarm.getPlayer().getXp()) + "<br/>" +
                "Level: " + Math.floor(myFarm.getPlayer().getLevel()) + "<br/>" +
                "Farmer type: " + myFarm.getPlayer().getFarmerType().getType() + "<br/></html>");
    }

    /**
     * Instantiates the tool buttons, as well as upgrade and next day, and adds it to
     * the tool panel.
     */
    public void insertActionButtons (){
        toolButton.add(pickaxeButton);
        toolButton.add(plowButton);
        toolButton.add(plantButton);
        toolButton.add(waterButton);
        toolButton.add(fertilizeButton);
        toolButton.add(shovelButton);
        toolButton.add(harvestButton);

        for (int i = 0; i < toolButton.size(); i++){
            tool.add(toolButton.get(i));
            toolButton.get(i).setBackground(new Color(61, 76, 83));
            toolButton.get(i).setForeground(Color.white);
            toolButton.get(i).setFocusable(false);
            toolButton.get(i).addActionListener(this);
            toolButton.get(i).setEnabled(false);
        }

        tool.add(upgradeButton);
        upgradeButton.setBackground(Color.white);
        upgradeButton.setForeground(new Color(230, 74, 69));
        upgradeButton.setFocusable(false);
        upgradeButton.addActionListener(this);

        tool.add(nextDay);
        nextDay.setBackground(Color.white);
        nextDay.setForeground(new Color(230, 74, 69));
        nextDay.setFocusable(false);
        nextDay.addActionListener(this);
    }

    /**
     * Inserts the tile status panel below the tool panel.
     */
    public void insertTileStatusPane(){
        frame.add(tileStatus);
        tileStatus.setLayout(null);
        tileStatus.setBounds(255,655, 100,190);
        tileStatus.setBackground(Color.white);
        tileStatus.setLineWrap(true);
        tileStatus.setEditable(false);
        tileStatus.setText("Pick a tile");
    }

    /**
     * Inserts the tile harvest panel beside the tile status panel.
     */
    public void insertTileHarvestStatusPane(){
        frame.add(tileHarvestStatus);
        tileHarvestStatus.setLayout(null);
        tileHarvestStatus.setBounds(365,655, 610,190);
        tileHarvestStatus.setBackground(Color.white);
        tileHarvestStatus.setLineWrap(true);
        tileHarvestStatus.setEditable(false);
    }

    /**
     * Instantiates and inserts the text pane to the left of the tile button panel.
     */
    public void insertTextPane(){
        frame.add(actionPanel);
        actionPanel.setLayout(null);
        actionPanel.setBackground(Color.white);
        actionPanel.setBounds(10,10,230,580);
        textPane.setEditable(false);
        textPane.setLineWrap(true);
        textPane.setWrapStyleWord(true);
        actionPanel.add(textPane);
        textPane.add(textPanelSP);
        textPanelSP.setBounds(211,0,20,580);
        textPane.setBounds(2,2,250,570);
        textPane.setPreferredSize(new Dimension(200,570));
        textPane.setBackground(Color.white);
        textPane.setText("Welcome!\n");


    }

    /**
     * Sets the proper measurements whenever another day has passed.
     */
    public void increaseDay(){
        myFarm.increaseDay(this);
        insertStats();
        textPane.setText("Day: " +myFarm.getDay() + "\n");
    }

    /**
     * Works with the actionPerformed() function. Picks the tile to be interacted with.
     * @param e     action event that listens when a tile button is pressed.
     */
    public void pickTile(ActionEvent e){
        for (int i = 0; i < tilesButtons.size(); i++){
            if (e.getSource() == tilesButtons.get(i)){
                myFarm.pickTile(i, this);
                textPane.append("Tile " + (i+1) + " has been chosen.\n");

            }
        }
        for (int i = 0; i < toolButton.size(); i++){
            toolButton.get(i).setEnabled(true);
        }
        tileHarvestStatus.setText("");
    }

    /**
     * Changes tile color and text according to state and crop.
     */
    public void changeTileColor (){
        for (int i = 0; i < myFarm.getTiles().size(); i++){

            if (!myFarm.getTiles().get(i).isPlowed() && !myFarm.getTiles().get(i).isWithered() && !myFarm.getTiles().get(i).isOccupied()){
                tilesButtons.get(i).setBackground(Color.white);
                tilesButtons.get(i).setText("" + (i+1));

            }

            if (myFarm.getTiles().get(i).isPlowed()){
                tilesButtons.get(i).setBackground(new Color(111, 78, 55));
            }
            if (myFarm.getTiles().get(i).isOccupied())
                tilesButtons.get(i).setText(myFarm.getTiles().get(i).getCrop().getName());

            if (myFarm.getTiles().get(i).isRocked())
                tilesButtons.get(i).setBackground(Color.gray);

            if (myFarm.getTiles().get(i).getCropLife() == 0 && myFarm.getTiles().get(i).getCrop() != null)
                tilesButtons.get(i).setBackground(Color.yellow);

            if (myFarm.getTiles().get(i).isWithered())
                tilesButtons.get(i).setBackground(Color.black);
        }
    }

    /**
     * Displays tile stats and check game validity.
     */
    public void displayTileStats(){
        System.out.println(myFarm.getCurrTile());
        if (myFarm.getCurrTile() != null){
            myFarm.getCurrTile().displayTileStatus();
            if (myFarm.getCurrTile().isOccupied())
                myFarm.getCurrTile().displayTileHarvestStatus();
        }
        myFarm.gameStateChecker(this);
    }

    /**
     * Disposes this frame.
     */
    public void dispose(){
        frame.dispose();
    }

    /**
     * In charge of listening to button presses and what sequence to do.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        pickTile(e);
        displayTileStats();
        myFarm.gameStateChecker(this);
        changeTileColor();


        if (e.getSource() == pickaxeButton){
            myFarm.usePickaxe(myFarm.getCurrTile(), this);
            insertStats();
            displayTileStats();
            changeTileColor();


        }

        if (e.getSource() == plowButton){
            myFarm.usePlow(myFarm.getCurrTile(), this);
            insertStats();
            displayTileStats();
            changeTileColor();

        }

        if (e.getSource() == plantButton){
            new plantDialog(myFarm, this);
            insertStats();
            displayTileStats();
            changeTileColor();

        }

        if (e.getSource() == waterButton){
            myFarm.waterTile(myFarm.getCurrTile(),this);
            insertStats();
            displayTileStats();
            changeTileColor();


        }

        if (e.getSource() == fertilizeButton){
            myFarm.fertilizeTile(myFarm.getCurrTile(),this);
            insertStats();
            displayTileStats();
            changeTileColor();


        }

        if (e.getSource() == shovelButton){
            myFarm.useShovel(myFarm.getCurrTile(), this);
            insertStats();
            displayTileStats();
            changeTileColor();


        }

        if (e.getSource() == harvestButton){
            myFarm.harvestCrop(myFarm.getCurrTile(), this);
            insertStats();
            displayTileStats();
            changeTileColor();


        }

        if (e.getSource() == upgradeButton){
            new farmDialog(myFarm, this);
        }

        if (e.getSource() == nextDay){
            increaseDay();
            changeTileColor();

        }

    }

    public JTextArea getTextPane() {
        return textPane;
    }

    public JTextArea getTileStatus() {
        return tileStatus;
    }

    public JTextArea getTileHarvestStatus() {
        return tileHarvestStatus;
    }

}
