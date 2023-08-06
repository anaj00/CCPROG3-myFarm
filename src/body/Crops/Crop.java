package body.Crops;

/*************************************************************************************************
 Crop       resides all crop related information

 name                       name of crop.
 type                       type of crop.
 harvestTime                crop's harvest time.
 waterNeeds                 crop's water needs.
 waterNeedsBonus            bonus water needs of crop.
 fertilizerNeeds            crop's fertilizer needs.
 fertilizerNeedsBonus       bonus fertilizer needs of crop.
 productsProducedMin        minimum amount of products per harvest.
 productsProducedMax        maximum amount of products per harvest.
 seedCost                   cost of seed.
 basePricePerPiece          price per piece.
 xpYield                    yield of harvesting crop.
 *************************************************************************************************/

public abstract class Crop {
    private String name;
    private String type;
    private int harvestTime;
    private int waterNeeds;
    private int waterNeedsBonus;
    private int fertilizerNeeds;
    private int fertilizerNeedsBonus;
    private int productsProducedMin;
    private int productsProducedMax;
    private int seedCost;
    private int basePricePerPiece;
    private double xpYield;

    public Crop(String name,
                String type,
                int harvestTime,
                int waterNeeds,
                int waterNeedsBonus,
                int fertilizerNeeds,
                int fertilizerNeedsBonus,
                int productsProducedMin,
                int productsProducedMax,
                int seedCost,
                int basePricePerPiece,
                double xpYield) {
        this.name = name;
        this.type = type;
        this.harvestTime = harvestTime;
        this.waterNeeds = waterNeeds;
        this.waterNeedsBonus = waterNeedsBonus;
        this.fertilizerNeeds = fertilizerNeeds;
        this.fertilizerNeedsBonus = fertilizerNeedsBonus;
        this.productsProducedMin = productsProducedMin;
        this.productsProducedMax = productsProducedMax;
        this.seedCost = seedCost;
        this.basePricePerPiece = basePricePerPiece;
        this.xpYield = xpYield;
    }

    // methods
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHarvestTime() {
        return harvestTime;
    }

    public int getWaterNeeds() {
        return waterNeeds;
    }

    public int getWaterNeedsBonus() {
        return waterNeedsBonus;
    }

    public int getFertilizerNeeds() {
        return fertilizerNeeds;
    }

    public int getFertilizerNeedsBonus() {
        return fertilizerNeedsBonus;
    }

    public int getProductsProducedMin() {
        return productsProducedMin;
    }

    public int getProductsProducedMax() {
        return productsProducedMax;
    }

    public int getSeedCost() {
        return seedCost;
    }

    public int getBasePricePerPiece() {
        return basePricePerPiece;
    }

    public double getXpYield() {
        return xpYield;
    }

    public void bonusEarningsPerProduce (int add){
        basePricePerPiece = basePricePerPiece + add;
    }

    public void seedCostReduction (int minus){
        seedCost = seedCost - minus;
    }

    public void waterBonusLimitIncrease (int add){
        waterNeedsBonus = waterNeedsBonus + add;
    }

    public void fertilizerBonusLimitIncrease (){
        fertilizerNeedsBonus = fertilizerNeedsBonus + 1;
    }
}
