import java.util.ArrayList;

/**
 * Contains statistics and methods relating to a player's armor.
 */
public class Armor {
    protected int armorDefense;
    protected int weight;
    protected ArrayList<String> element = new ArrayList<>();
    {
        element.add("STATUS");
        element.add("POISON_STONE");
        element.add("TIME");
        element.add("DEATH");
        element.add("FIRE");
        element.add("ICE");
        element.add("LIGHTNING");
        element.add("EARTH");
    }
    // Potentially add current element(s)
}
