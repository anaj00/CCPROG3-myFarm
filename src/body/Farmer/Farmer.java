package body.Farmer; /*************************************************************************************************
 Farmer     class that has all farmer related information.

 type                           type of farmer.
 levelReq                       level requirement of the farmer.
 bonusEarningsPerProduce        additional bonus per produce.
 seedCostReduction              reduction of cost per seed.
 waterBonusLimitIncrease        additional bonus for water bonus.
 fertilizerBonusLimitIncrease   additional bonus for fertilizer bonus.
 registrationFee                cost of how much to register.
 *************************************************************************************************/

public abstract class Farmer {
    private String type;
    private int levelReq;
    private int bonusEarningsPerProduce;
    private int seedCostReduction;
    private int waterBonusLimitIncrease;
    private int fertilizerBonusLimitIncrease;
    private double registrationFee;

    public Farmer(String type, int levelReq, int bonusEarningsPerProduce, int seedCostReduction, int waterBonusLimitIncrease, int fertilizerBonusLimitIncrease, double registrationFee) {
        this.type = type;
        this.levelReq = levelReq;
        this.bonusEarningsPerProduce = bonusEarningsPerProduce;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusLimitIncrease = waterBonusLimitIncrease;
        this.fertilizerBonusLimitIncrease = fertilizerBonusLimitIncrease;
        this.registrationFee = registrationFee;
    }

    public String getType() {
        return type;
    }

    public int getBonusEarningsPerProduce() {
        return bonusEarningsPerProduce;
    }
}
