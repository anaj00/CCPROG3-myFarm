import body.Farmer.*;

import java.util.Scanner;

/*************************************************************************************************
 Where all player info resides.

 name           player name.
 objectCoins    player's wallet.
 xp             player's XP.
 level          player's level.
 farmerType     player's farmer type.
 *************************************************************************************************/

public class PlayerInfo {
    private String name;
    private double objectCoins;
    private double xp;
    private double level = xp / 100;
    private Farmer farmerType;

    public PlayerInfo(String name){
        this.name = name;
        objectCoins = 100;
        xp = 999;
        level = xp / 100;
        farmerType = new DefaultFarmer();
    }

    /**
     * Does farmer type upgrade sequence.
     * @param choice        farmer type to be upgraded to.
     * @param tileGUI       GUI to be displayed.
     */
    public void farmerTypeUpdate (Farmer choice, tileGUI tileGUI){
        boolean flag = false;

        if (choice.getType() == new DefaultFarmer().getType()){
            if (farmerChecker(choice)){
                tileGUI.getTextPane().append("You are already a " + farmerType.getType() + "\n");
            }
            else {
                farmerType = new DefaultFarmer();
                tileGUI.getTextPane().append("You are now a " + farmerType.getType() + "\n");
                flag = true;
            }
        }

        else if (choice.getType() == new RegisteredFarmer().getType()){
            if (farmerChecker(choice)){
                tileGUI.getTextPane().append("You are already a " + farmerType.getType() + "\n");
            }
            else {
                if (level < 5){
                    tileGUI.getTextPane().append("You are not eligible for this type as your level is too low.\n");
                }
                else {
                    if (objectCoins > 200) {
                        farmerType = new RegisteredFarmer();
                        objectCoins -= 200;
                        tileGUI.getTextPane().append("You are now a " + farmerType.getType() + ", 200 Objectcoins was taken from your wallet\n");
                        flag = true;
                    }
                    else tileGUI.getTextPane().append("You do not have enough money to register.\n");
                }
            }
        }
        else if (choice.getType() == new DistinguishedFarmer().getType()){
            if (farmerChecker(choice)){
                tileGUI.getTextPane().append("You are already a " + farmerType.getType() + "\n");
            }
            else {
                if (level < 10){
                    tileGUI.getTextPane().append("You are not eligible for this type as your level is too low.\n");
                }
                else {
                    if (objectCoins > 300) {
                        farmerType = new DistinguishedFarmer();
                        objectCoins -= 300;
                        tileGUI.getTextPane().append("You are now a " + farmerType.getType() + ", 300 Objectcoins was taken from your wallet\n");
                        flag = true;
                    }
                    else tileGUI.getTextPane().append("You do not have enough money to register.\n");
                }
            }
        }
        else if (choice.getType() == new LegendaryFarmer().getType()){
            if (farmerChecker(choice)){
                System.out.println("You are already a " + farmerType.getType() + "\n");
            }
            else {
                if (level < 15){
                    System.out.println("You are not eligible for this type as your level is too low.\n");
                }
                else {
                    if (objectCoins > 400){
                        farmerType = new LegendaryFarmer();
                        objectCoins -= 400;
                        System.out.println("You are now a " + farmerType.getType() + ", 400 Objectcoins was taken from your wallet\n");
                        flag = true;
                    }
                    else System.out.println("You do not have enough money to register.\n");
                }
            }
        }
    }

    /**
     * Checks if the farmer type to be upgraded is the same as the current farmer type the user has.
     * @param newFarmerType     farmer type to be upgraded.
     * @return                  returns true if it is the same as the current farmer type,
     *                          else false.
     */
    public boolean farmerChecker(Farmer newFarmerType){
        if (newFarmerType.getType() == farmerType.getType())
            return true;
        return false;
    }

    /**
     * Computes the level in terms of XP.
     * @return      level.
     */
    public double getLevelCompute(){
        return xp/100;
    }



    // objectCoins
    public double getObjectCoins() {
        return objectCoins;
    }
    public void addObjectCoins (double add){
        objectCoins = objectCoins + add;
    }
    public void minusObjectCoins (int subtract){
        objectCoins = objectCoins - subtract;
    }

    // XP
    public void addXp(double gain){
        xp = xp + gain;
    }
    public String getName() {
        return name;
    }

    public double getXp() {
        return xp;
    }

    public double getLevel() {
        return level;
    }

    public Farmer getFarmerType() {
        return farmerType;
    }


}

