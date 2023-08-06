import body.FarmTools.*;
import body.Crops.*;

/**
 * Holds crops, tools, and even farmer upgrades.
 */
public class Inventory {
    private Crop turnip = new Turnip();
    private Crop carrot = new Carrot();
    private Crop potato = new Potato();
    private Crop rose = new Rose();
    private Crop tulips = new Tulips();
    private Crop sunflower = new Sunflower();
    private Crop mango = new Mango();
    private Crop apple = new Apple();

    private Tool plow = new Plow();
    private Tool wateringCan = new WateringCan();
    private Tool fertilizer = new Fertilizer();
    private Tool pickaxe = new Pickaxe();
    private Tool shovel = new Shovel();

    public void defaultFarmerBonus(){
        turnip = new Turnip();
        carrot = new Carrot();
        potato = new Potato();
        rose = new Rose();
        tulips = new Tulips();
        sunflower = new Sunflower();
        mango = new Mango();
        apple = new Apple();
    }

    /**
     * Does the appropriate bonus additions when registered as a registered farmer.
     */
    public void registeredFarmerBonus(){
        turnip.bonusEarningsPerProduce(1);
        turnip.seedCostReduction(1);

        carrot.bonusEarningsPerProduce(1);
        carrot.seedCostReduction(1);

        potato.bonusEarningsPerProduce(1);
        potato.seedCostReduction(1);

        rose.bonusEarningsPerProduce(1);
        rose.seedCostReduction(1);

        tulips.bonusEarningsPerProduce(1);
        tulips.seedCostReduction(1);

        sunflower.bonusEarningsPerProduce(1);
        sunflower.seedCostReduction(1);

        mango.bonusEarningsPerProduce(1);
        mango.seedCostReduction(1);

        apple.bonusEarningsPerProduce(1);
        apple.seedCostReduction(1);
    }

    /**
     * Does the appropriate bonus additions when registered as a distinguished farmer.
     */
    public void distinguishedFarmerBonus(){
        turnip.bonusEarningsPerProduce(2);
        turnip.seedCostReduction(2);
        turnip.waterBonusLimitIncrease(1);

        carrot.bonusEarningsPerProduce(2);
        carrot.seedCostReduction(2);
        carrot.waterBonusLimitIncrease(1);

        potato.bonusEarningsPerProduce(2);
        potato.seedCostReduction(2);
        potato.waterBonusLimitIncrease(1);

        rose.bonusEarningsPerProduce(2);
        rose.seedCostReduction(2);
        rose.waterBonusLimitIncrease(1);

        tulips.bonusEarningsPerProduce(2);
        tulips.seedCostReduction(2);
        tulips.waterBonusLimitIncrease(1);

        sunflower.bonusEarningsPerProduce(2);
        sunflower.seedCostReduction(2);
        sunflower.waterBonusLimitIncrease(1);

        mango.bonusEarningsPerProduce(2);
        mango.seedCostReduction(2);
        mango.waterBonusLimitIncrease(1);

        apple.bonusEarningsPerProduce(2);
        apple.seedCostReduction(2);
        apple.waterBonusLimitIncrease(1);
    }


    /**
     * Does the appropriate bonus additions when registered as a legendary farmer.
     */
    public void legendaryFarmerBonus(){
        turnip.bonusEarningsPerProduce(4);
        turnip.seedCostReduction(3);
        turnip.waterBonusLimitIncrease(2);
        turnip.fertilizerBonusLimitIncrease();

        carrot.bonusEarningsPerProduce(4);
        carrot.seedCostReduction(3);
        carrot.waterBonusLimitIncrease(2);
        carrot.fertilizerBonusLimitIncrease();

        potato.bonusEarningsPerProduce(4);
        potato.seedCostReduction(3);
        potato.waterBonusLimitIncrease(2);
        potato.fertilizerBonusLimitIncrease();

        rose.bonusEarningsPerProduce(4);
        rose.seedCostReduction(3);
        rose.waterBonusLimitIncrease(2);
        rose.fertilizerBonusLimitIncrease();

        tulips.bonusEarningsPerProduce(4);
        tulips.seedCostReduction(3);
        tulips.waterBonusLimitIncrease(2);
        tulips.fertilizerBonusLimitIncrease();

        sunflower.bonusEarningsPerProduce(4);
        sunflower.seedCostReduction(3);
        sunflower.waterBonusLimitIncrease(2);
        sunflower.fertilizerBonusLimitIncrease();

        mango.bonusEarningsPerProduce(4);
        mango.seedCostReduction(3);
        mango.waterBonusLimitIncrease(2);
        mango.fertilizerBonusLimitIncrease();

        apple.bonusEarningsPerProduce(4);
        apple.seedCostReduction(3);
        apple.waterBonusLimitIncrease(2);
        apple.fertilizerBonusLimitIncrease();
    }

    public Crop getTurnip() {
        return turnip;
    }

    public Crop getCarrot() {
        return carrot;
    }

    public Crop getPotato() {
        return potato;
    }

    public Crop getRose() {
        return rose;
    }

    public Crop getTulips() {
        return tulips;
    }

    public Crop getSunflower() {
        return sunflower;
    }

    public Crop getMango() {
        return mango;
    }

    public Crop getApple() {
        return apple;
    }

    public Tool getPlow() {
        return plow;
    }

    public Tool getWateringCan() {
        return wateringCan;
    }

    public Tool getFertilizer() {
        return fertilizer;
    }

    public Tool getPickaxe() {
        return pickaxe;
    }

    public Tool getShovel() {
        return shovel;
    }

}
