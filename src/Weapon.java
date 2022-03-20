import java.util.ArrayList;

/**
 * Contains statistics and methods relating to a player's weapon.
 */
public class Weapon {
    protected int index;
    protected int weaponAttack;
    protected int weaponAccuracy;
    protected ArrayList<String> enemyFamilyTypes = new ArrayList<>();
    {
        enemyFamilyTypes.add("MAGICAL");
        enemyFamilyTypes.add("DRAGOYN");
        enemyFamilyTypes.add("GIANT");
        enemyFamilyTypes.add("UNDEAD");
        enemyFamilyTypes.add("WERE");
        enemyFamilyTypes.add("AQUATIC");
        enemyFamilyTypes.add("MAGE");
        enemyFamilyTypes.add("REGENERATIVE");
    }
    protected ArrayList<String> currentEnemyFamilyTypes = new ArrayList<>();
    // Potentially add current enemyFamilyType(s)
    protected ArrayList<String> elements = new ArrayList<>();
    {
        elements.add("STATUS");
        elements.add("POISON_STONE");
        elements.add("TIME");
        elements.add("DEATH");
        elements.add("FIRE");
        elements.add("ICE");
        elements.add("LIGHTNING");
        elements.add("EARTH");
    }
    protected ArrayList<String> currentElements = new ArrayList<>();
    protected int criticalRate;
    protected ArrayList<String> equipabble = new ArrayList<>();
}