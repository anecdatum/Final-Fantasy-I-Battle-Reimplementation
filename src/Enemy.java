import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class which contains enemy characteristics and methods.
 */
public class Enemy {
    protected Battle associatedBattle;

    // Enemy statistics

    protected ArrayList<String> name = new ArrayList<>();
    protected int HP;
    protected int attack;
    protected int accuracy;
    protected int numberOfHits;
    protected int criticalRate;
    protected int defense;
    protected int evasion;
    protected int magicDefense;
    protected int morale;
    protected ArrayList<String> statusAttack = new ArrayList<>();
    protected ArrayList<String> statusAttackElement = new ArrayList<>();
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
    protected ArrayList<String> weaknesses = new ArrayList<>();
    protected ArrayList<String> resistances = new ArrayList<>();
    protected int magic;
    protected int skill;
    protected int gold;
    protected int XP;
    // protected ArrayList<String> formations = new ArrayList<>(); temporary; potentially additional object needed
    protected ArrayList<String> locations = new ArrayList<>();
}
