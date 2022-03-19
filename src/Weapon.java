import java.util.ArrayList;

/**
 * Contains statistics and methods relating to a player's weapon.
 */
public class Weapon {
    protected int index;
    protected int weaponAttack;
    protected int weaponAccuracy;
    protected ArrayList<String> enemyFamilyType = new ArrayList<>();
    {
        enemyFamilyType.add("MAGICAL");
        enemyFamilyType.add("DRAGOYN");
        enemyFamilyType.add("GIANT");
        enemyFamilyType.add("UNDEAD");
        enemyFamilyType.add("WERE");
        enemyFamilyType.add("AQUATIC");
        enemyFamilyType.add("MAGE");
        enemyFamilyType.add("REGENERATIVE");
    }
    // Potentially add current enemyFamilyType(s)
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
    protected int criticalRate;
    protected ArrayList<String> equipabble = new ArrayList<>();
}