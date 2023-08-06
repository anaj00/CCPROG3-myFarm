package body.FarmTools;

/*************************************************************************************************
 Tool       class that has all tool related information.

 name           name of tool.
 costOfUsage    tool's cost of usage.
 xpGain         tool's xp gain.
 *************************************************************************************************/

public abstract class Tool {
    private String name;
    private int costOfUsage;
    private double xpGain;

    public Tool(String name, int costOfUsage, double xpGain) {
        this.name = name;
        this.costOfUsage = costOfUsage;
        this.xpGain = xpGain;
    }

    public String getName() {
        return name;
    }

    public int getCostOfUsage() {
        return costOfUsage;
    }

    public double getXpGain() {
        return xpGain;
    }
}
