import body.Crops.Crop;
import body.Farmer.Farmer;

import java.util.ArrayList;
import java.util.Scanner;


/*************************************************************************************************
 Where all tile information resides in.

 number                     unique number of a tile.
 isPlowed                   if tile is either plowed or not.
 isOccupied                 if tile is occupied or not.
 isWithered                 if tile is withered or not.
 isRocked                   if tile has a rock or not.
 crop                       certain crop planted on a tile.
 cropLife                   crop's life of the tile.
 waterLevels                tile's water level.
 tempWaterLevels            tile's temporary water level for a day.
 fertilizerLevels           tile's fertilizer level.
 tempFertilizerLevels       tile's temporary fertilizer level for a day.
 *************************************************************************************************/

public class Tile {
    private int number;
    private boolean isPlowed = false;
    private boolean isOccupied = false;
    private boolean isWithered = false;
    private boolean isRocked = false;
    private Crop crop;
    private int cropLife = 0;
    private int waterLevels = 0;
    private int tempWaterLevels = 0;
    private int fertilizerLevels = 0;
    private int tempFertilizerLevels = 0;

    tileGUI tileGUI;
    myFarm myFarm;

    // constructor
    public Tile (int number, tileGUI tileGUI, myFarm myFarm){
        this.number = number;
        this.tileGUI = tileGUI;
        this.myFarm = myFarm;

    }

    /**
     * Displays tile harvest status in panel.
     */
    public void displayTileHarvestStatus() {
        if (!isWithered){
            tileGUI.getTileHarvestStatus().setText("Crop: "+ crop.getName() + "\n");
            if (cropLife == 0){
                tileGUI.getTileHarvestStatus().append("Tile is ready to be harvested!");
            }
            else {
                tileGUI.getTileHarvestStatus().append(cropLife + " day(s) till harvest day."+ "\n");
            }
            tileGUI.getTileHarvestStatus().append("Water levels: " + waterLevels + " / " + crop.getWaterNeeds() + " (" + crop.getWaterNeedsBonus() + ")\n");
            tileGUI.getTileHarvestStatus().append("Fertilizer levels: " + fertilizerLevels + " / " + crop.getFertilizerNeeds() + " (" + crop.getFertilizerNeedsBonus()+ ")\n");
        }
        else if (isWithered){
            tileGUI.getTileHarvestStatus().append("Crop: Withered " + crop.getName() + "\n");
            tileGUI.getTileHarvestStatus().append("To proceed, please remove withered crop using shovel.\n");
        }
    }

    /**
     * Displays tile status in panel.
     */
    public void displayTileStatus(){
        tileGUI.getTileStatus().setText("Rocked: " + isRocked + "\n");
        tileGUI.getTileStatus().append("Occupied: " + isOccupied + "\n");
        tileGUI.getTileStatus().append("Withered: " + isWithered + "\n");
        tileGUI.getTileStatus().append("\n");
    }

    /**
     * Does proper measurements for whenever a day has passed.
     */
    public void increaseStatsforTiles(){
        if (isOccupied){
            cropLife = cropLife - 1;
            tempWaterLevels = 0;
            tempFertilizerLevels = 0;
        }

        if (ifWithered()){
            isWithered = true;
            isOccupied = true;
        }
    }

    /**
     * Displays plow tile sequence.
     * @return      true if tile was plowed.
     *              else false.
     */
    public boolean plowTile (){

        tileGUI.getTextPane().append("Tile " + (number + 1) + " is ");
        if (!isPlowed && !isWithered && !isRocked){
            tileGUI.getTextPane().append("not plowed. Tile will now be plowed.\n");
            isPlowed = true;
            return true;
        }
        else if (isRocked){
            tileGUI.getTextPane().append("occupied with a rock. Use a pickaxe to remove the rock.\n");
            return false;
        }

        else if (isWithered){
            tileGUI.getTextPane().append("filled with a withered crop. Cannot plow tile.\n");
            return false;
        }

        else {
            tileGUI.getTextPane().append("already plowed. Tile will not be plowed as it already is.\n");
            return false;
        }
    }

    /**
     * Displays plant tile sequence.
     * @param choice    crop to be planted.
     * @return          true if a crop was planted,
     *                  else false.
     */
    public boolean plantSeed(Crop choice) {
        if (choice.getSeedCost() > myFarm.getPlayer().getObjectCoins()){
            tileGUI.getTextPane().append("Insufficient money.\n");
            return false;
        }
        tileGUI.getTextPane().append("Tile " + (number + 1) + " is ");

        if (isPlowed && !isWithered) {
            if (!isOccupied) {
                tileGUI.getTextPane().append("empty.\n");

                if (myFarm.getPlayer().getObjectCoins() >= choice.getSeedCost()) {
                    if (choice == myFarm.getInventory().getMango() || choice == myFarm.getInventory().getApple()) {
                        if (checkTree(myFarm.getTiles(), number)) {
                            tileGUI.getTextPane().append("Cannot plant a tree here.\n");
                            return false;
                        }
                    }
                    crop = choice;
                    tileGUI.getTextPane().append("\nTile " + (number + 1) + " now has a(n) " + crop.getName() + " growing.\n");
                    isOccupied = true;
                    cropLife = crop.getHarvestTime();
                    return true;
                }
            }
            tileGUI.getTextPane().append("occupied. Cannot plant a seed.\n");
            return false;
        }
        else if (isWithered) {
            tileGUI.getTextPane().append("filled with a withered crop. Cannot plant a seed.\n");
            return false;
        }

        tileGUI.getTextPane().append("not plowed.\n");
        return false;
    }

    /**
     * Displays water tile sequence.
     * @return      true if tile was watered,
     *              else false.
     */
    public boolean waterTile (){
        if (isOccupied && !isWithered){
            if (tempWaterLevels == 0){
                if (cropLife == 0){
                    tileGUI.getTextPane().append("Tile cannot be watered on harvest day.\n");
                    return false;
                }
                tileGUI.getTextPane().append("Tile has not been watered today. Watering tile...");
                waterLevels++;
                tempWaterLevels++;
                return true;
            }
            tileGUI.getTextPane().append("Tile has been watered. Come again tomorrow to water it.\n");
            return false;
        }
        else if (isWithered){
            tileGUI.getTextPane().append("Tile is filled with a withered crop. Cannot water tile.\n");
        }
        tileGUI.getTextPane().append("Tile has no crop to water.\n");
        return false;
    }

    /**
     * Displays tile fertilizing sequence.
     * @return      true if tile was fertilized,
     *              else false.
     */
    public boolean fertilizeTile() {
        if (isOccupied && !isWithered) {
            if (tempFertilizerLevels == 0) {
                if (cropLife == 0){
                    tileGUI.getTextPane().append("Tile cannot be fertilized on harvest day.\n");
                    return false;
                }
                if (myFarm.getPlayer().getObjectCoins() < myFarm.getInventory().getFertilizer().getCostOfUsage()){
                    tileGUI.getTextPane().append("Insufficient money.\n");
                    return false;
                }
                tileGUI.getTextPane().append("Tile has not been fertilized today. Fertilizing tile...");
                fertilizerLevels++;
                tempFertilizerLevels++;
                return true;
            }
            tileGUI.getTextPane().append("Tile has been fertilized. Come again tomorrow to fertilize it.\n");
            return false;
        }

        else if (isWithered){
            tileGUI.getTextPane().append("Tile is filled with a withered crop. Cannot fertilize tile.\n");
        }
        tileGUI.getTextPane().append("Tile has no crop to fertilize.\n");
        return false;
    }

    /**
     * Displays shovel tool sequence.
     * @return      true if shovel was used,
     *              else false.
     */
    public boolean useShovel() {
        if (myFarm.getInventory().getShovel().getCostOfUsage() > myFarm.getPlayer().getObjectCoins()){
            tileGUI.getTextPane().append("Insufficient money.\n");
            return false;
        }
        if (isOccupied) {
            if (!isWithered) {
                tileGUI.getTextPane().append("Removing " + crop.getName() + "...");
                resetAll();
                return true;
            }
        }
        if (isWithered) {
                tileGUI.getTextPane().append("Removing withered crop...");
                resetAll();
                return true;

        } else if (!isPlowed || isRocked) {
                tileGUI.getTextPane().append("Oh no! You can't use that here!\n");
                return true;
        }
        tileGUI.getTextPane().append("Tile is not occupied with any crop to dig.\n");
        return false;
    }

    /**
     * Displays pickaxe tool sequence.
     * @return      true if pickaxe was used,
     *              else false.
     */
    public boolean usePickaxe(){
        if (myFarm.getInventory().getPickaxe().getCostOfUsage() > myFarm.getPlayer().getObjectCoins()){
            tileGUI.getTextPane().append("Insufficient money.\n");
            return false;
        }
        if (isRocked){
            tileGUI.getTextPane().append("Removing rock on tile " + (number+1) + "\n");
            isRocked = false;
            return true;
        }

        tileGUI.getTextPane().append("There is no rock for the pickaxe to remove in the tile.\n");
        return false;
    }

    /**
     * Displays crop harvesting sequence.
     * @return      true if crop was harvested,
     *              else false.
     */
    public boolean harvestCrop(){
        if (isOccupied){
            if (!isWithered){
                if (cropLife == 0){
                    tileGUI.getTextPane().append("Harvesting " + crop.getName() + "...\n");
                    return true;
                }
                else {
                    tileGUI.getTextPane().append("Tile is not ready to be harvested.\n");
                    return false;
                }
            }
            else if(isWithered){
                tileGUI.getTextPane().append("Cannot harvest a withered tile.\n");
                return false;
            }
        }
        tileGUI.getTextPane().append("Tile is not occupied with any crop to harvest.\n");
        return false;

    }

    /**
     * Computes for final harvest price.
     * @param farmerType        farmer type of player.
     * @return                  final harvest price.
     */
    public double finalHarvestPrice(Farmer farmerType){
        int productsProduced = productsProduced();
        double harvestTotal = 0, waterBonus = 0, fertilizerBonus = 0;
        harvestTotal = productsProduced * (crop.getBasePricePerPiece() + farmerType.getBonusEarningsPerProduce());
        tileGUI.getTextPane().append("You harvested " + productsProduced + " " + crop.getName() + "(s" + ") " + "(" + crop.getBasePricePerPiece() + " Objectcoins/" + crop.getName()+ ")\n" );
        if (waterLevels >= crop.getWaterNeedsBonus()){
            tileGUI.getTextPane().append("Water bonus applied!\n");
            waterBonus = harvestTotal * 0.2 * (waterLevels - 1);
        }
        if (fertilizerLevels >= crop.getFertilizerNeedsBonus()){
            tileGUI.getTextPane().append("Fertilizer bonus applied!\n");
            fertilizerBonus = harvestTotal * 0.5 * (fertilizerLevels);
        }

        return harvestTotal + waterBonus + fertilizerBonus;
    }

    /**
     * Randomizer for products produced.
     * @return      how many products, ranging from its minimum to maximum yield.
     */
    public int productsProduced() {
        int min = crop.getProductsProducedMin(), max = crop.getProductsProducedMax(), random;
        random = (int) Math.floor (Math.random() * (max-min + 1) + min);

        return random;
    }

    /**
     * Resets a tile to default.
     */
    public void resetAll(){
        isPlowed = false;
        isOccupied = false;
        isWithered = false;
        isRocked = false;
        crop = null;
        cropLife = 0;
        waterLevels = 0;
        tempWaterLevels = 0;
        fertilizerLevels = 0;
        tempFertilizerLevels = 0;
    }

    /**
     * Checks whether a tile is withered or not.
     * @return      true if tile is withered,
     *              else false.
     */
    public boolean ifWithered (){
        if (crop != null){
            if (cropLife < 0){
                return true;
            }
            else if (cropLife == 0 && (waterLevels < crop.getWaterNeeds() | fertilizerLevels < crop.getFertilizerNeeds())){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if properties of planting a tree are satisfied.
     * @param tiles         arrayList of tiles.
     * @param currTile      number of current tile, where tree is to be planted.
     * @return              returns true if tree cannot be planted,
     *                      else false if tree can be planted.
     */
    public boolean checkTree(ArrayList<Tile> tiles, int currTile){
        if (checkCorners(currTile)){
            return true;
        }

        boolean topLeft = tiles.get(currTile - 11).isOccupied || tiles.get(currTile - 11).isWithered || tiles.get(currTile - 11).isRocked;
        boolean top = tiles.get(currTile - 10).isOccupied || tiles.get(currTile - 10).isWithered || tiles.get(currTile - 10).isRocked;
        boolean topRight = tiles.get(currTile - 9).isOccupied || tiles.get(currTile - 9).isWithered || tiles.get(currTile - 9).isRocked;
        boolean left = tiles.get(currTile - 1).isOccupied || tiles.get(currTile - 1).isWithered || tiles.get(currTile - 1).isRocked;
        boolean right = tiles.get(currTile + 1).isOccupied || tiles.get(currTile + 1).isWithered || tiles.get(currTile + 1).isRocked;
        boolean bottomLeft = tiles.get(currTile + 9).isOccupied || tiles.get(currTile + 9).isWithered || tiles.get(currTile + 9).isRocked;
        boolean bottom = tiles.get(currTile + 10).isOccupied || tiles.get(currTile + 10).isWithered || tiles.get(currTile + 10).isRocked;
        boolean bottomRight = tiles.get(currTile + 11).isOccupied || tiles.get(currTile + 11).isWithered || tiles.get(currTile + 11).isRocked;

        return topLeft || top || topRight || left || right || bottomLeft || bottom || bottomRight;
    }

    /**
     * Checks if tile is in the corners of the play area.
     * @param num       tile to be checked.
     * @return          returns true if tile is in the corner,
     *                  else false.
     */
    public boolean checkCorners (int num){
        if (num < 10 || num > 39 || num % 10 == 0 || num % 10 == 9)
            return true;
        else return false;
    }




    // getters and setters
    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isWithered() {
        return isWithered;
    }

    public boolean isRocked() {
        return isRocked;
    }

    public void setRocked(boolean rocked) {
        isRocked = rocked;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public int getCropLife() {
        return cropLife;
    }

    public boolean isPlowed() {
        return isPlowed;
    }
}

