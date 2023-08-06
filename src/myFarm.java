

/*************************************************************************************************
 MyFarm     Main class that resides all the subclasses.

 inventory      where all tools and seeds are instantiated in.
 reset          0.
 flag           whether the game should continue, or end.
 day            day in the game.
 player         where all player info resides.
 tiles          an arrayList of all the tiles in the game.
 *************************************************************************************************/


import body.Crops.Crop;
import body.FarmTools.Tool;
import body.Farmer.Farmer;

import java.io.FileReader;
import java.util.ArrayList;

/**
 * Contains the whole myFarm object and methods.
 */
public class myFarm {

    private Inventory inventory = new Inventory();
    private final int reset = 0;
    private boolean flag = true;
    private int day = 1;
    private PlayerInfo player;
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private Tile currTile;


    //methods
    public void increaseDay(tileGUI tileGUI){
        day = day + 1;
        for (int i = 0; i < tiles.size(); i++){
            tiles.get(i).increaseStatsforTiles();
        }
        gameStateChecker(tileGUI);
    }


    public void pickTile(int index, tileGUI tileGUI){
        currTile = tiles.get(index);
    }

    /**
     * Runs sequence for when user wants to plow tile.
     * @param tile      tile to be plowed.
     */
    public void usePlow(Tile tile, tileGUI tileGUI){
        if (tile.plowTile()){
            useTool(inventory.getPlow(), tileGUI);
        }
    }

    public void plantSeed(Crop choice, tileGUI tileGUI){
        if (currTile.plantSeed(choice)){
            tileGUI.getTextPane().append("Cost of planting is " + currTile.getCrop().getSeedCost() + " Objectcoins\n");
            player.minusObjectCoins(currTile.getCrop().getSeedCost());

        }
    }

    /**
     * Runs sequence for when user wants to water the tile.
     * @param tile      tile to be watered.
     */
    public void waterTile(Tile tile, tileGUI tileGUI){
        if (tile.waterTile()){
            useTool(inventory.getWateringCan(), tileGUI);
        }
    }
//
    /**
     * Runs sequence for when user wants to fertilize the tile.
     * @param tile      tile to be fertilized.
     */
    public void fertilizeTile(Tile tile, tileGUI tileGUI){
        if (tile.fertilizeTile()){
            useTool(inventory.getFertilizer(), tileGUI);
        }
    }

    /**
     * Runs sequence for when user wants to use a shovel on a tile.
     * @param tile      tile to be used a shovel on.
     */
    public void useShovel (Tile tile, tileGUI tileGUI){
        if (tile.useShovel()){
            useTool(inventory.getShovel(), tileGUI);
        }
    }

    /**
     * Runs sequence for when user wants to use a pickaxe on a tile.
     * @param tile      tile to be used a pickaxe on.
     */
    public void usePickaxe (Tile tile, tileGUI tileGUI){
        if (tile.usePickaxe()){
            useTool(inventory.getPickaxe(), tileGUI);
        }
    }

    /**
     * Runs sequence for when user wants to harvest the crop on a tile.
     * @param tile      tile to be harvested.
     */
    public void harvestCrop (Tile tile, tileGUI tileGUI){
        double finalHarvestPrice;
        if (tile.harvestCrop()){
            finalHarvestPrice = tile.finalHarvestPrice(player.getFarmerType());
            tileGUI.getTextPane().append("You have earned " + finalHarvestPrice + " Objectcoins and " + tile.getCrop().getXpYield() + "XP!");
            if (tile.getCrop().getType() == "Flower"){
                finalHarvestPrice = finalHarvestPrice * 1.1;
                tileGUI.getTextPane().append("Since all flowers are pretty, you get a bonus! Your new earnings are " + finalHarvestPrice + " Objectcoins!");
            }

            tileGUI.getTextPane().append("\n");
            player.addObjectCoins(finalHarvestPrice);
            player.addXp(tile.getCrop().getXpYield());
            tile.resetAll();
        }
    }

    /**
     * Displays the cost of usage, and XP yield of a certain tool.
     * @param tool      tool to be displayed.
     */
    public void useTool(Tool tool, tileGUI tileGUI){
        tileGUI.getTextPane().append("Cost of usage is " + tool.getCostOfUsage() + ", and XP gain is " + tool.getXpGain() + ".\n");
        player.minusObjectCoins(tool.getCostOfUsage());
        player.addXp(tool.getXpGain());
    }

    public void readRockFile (){
        char[] array = new char[58];

        try {
            FileReader input = new FileReader("rocks.txt");

            input.read(array);

            for (int arr = 0, tile = 0; arr < array.length; arr++, tile++){
                if (arr == 10 || arr == 22 || arr == 34 || arr == 46){
                    arr = arr + 2;
                }
                if (array[arr] == 'X'){
                    tiles.get(tile).setRocked(true);
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Gives respective upgrade if farmer type was upgraded.
     */
    public void farmerUpgrade (Farmer choice, tileGUI tileGUI){
        System.out.println(player.getFarmerType());

        player.farmerTypeUpdate(choice, tileGUI);
        if (player.getFarmerType().getType() == "Default Farmer"){
            inventory.defaultFarmerBonus();
        }
        else if (player.getFarmerType().getType() == "Registered Farmer"){
            inventory.registeredFarmerBonus();
        }
        else if (player.getFarmerType().getType() == "Distinguished Farmer"){
            inventory.distinguishedFarmerBonus();
        }
        else if (player.getFarmerType().getType() == "Legendary Farmer"){
            inventory.legendaryFarmerBonus();
        }
    }

    /**
     * Checks whether the state of the game is enough to continue.
     */
    public void gameStateChecker(tileGUI tileGUI){
        boolean objectCoinsBool = false;
        boolean tilesBool = true;

        if (player.getObjectCoins() < 5){
            objectCoinsBool = true;
            flag = false;
        }

        for (int i = 0; i < 50; i++){
            if (tiles.get(i).isOccupied() && !tiles.get(i).isWithered()){
                tilesBool = false;
                break;
            }
        }

        if (objectCoinsBool && tilesBool){
            tileGUI.getTextPane().append("Oh no! Game over");
            flag = false;
            tileGUI.dispose();
            new gameOverDialog();
        }
    }




    // getters and setters
    public Inventory getInventory() {
        return inventory;
    }

    public Tile getCurrTile() {
        return currTile;
    }

    public int getDay() {
        return day;
    }

    public PlayerInfo getPlayer() {
        return player;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setPlayer(PlayerInfo player) {
        this.player = player;
    }

}


