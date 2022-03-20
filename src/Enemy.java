import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class which contains enemy characteristics and methods.
 */
public class Enemy {
    protected Battle associatedBattle;
    protected GameState associatedGameState;

    // Enemy statistics

    protected ArrayList<String> names = new ArrayList<>();
    protected String currentName;
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
    protected ArrayList<String> status = new ArrayList<>();
    {
        status.add("DEATH");
        status.add("PETRIFICATION");
        status.add("POISON");
        status.add("BLIND");
        status.add("PARALYSIS");
        status.add("SLEEP");
        status.add("SILENCE");
    }
    protected ArrayList<String> currentStatuses = new ArrayList<>();
    protected ArrayList<String> weaknesses = new ArrayList<>();
    protected ArrayList<String> resistances = new ArrayList<>();
    protected int magic;
    protected int skill;
    protected int gold;
    protected int XP;
    // protected ArrayList<String> formations = new ArrayList<>(); temporary; potentially additional object needed
    protected ArrayList<String> locations = new ArrayList<>();

    // Battle methods

    /**
     * Initiates attempt for an enemy to run from battle
     * @param enemy Enemy calling the run command
     * @throws IllegalStateException occurs if there is no leader
     */
    public void run(Enemy enemy) throws IllegalStateException {
        int leaderLevel = 0; // integer for the level of the leader of classes
        for (BaseClass baseClass : associatedBattle.getClasses()) {
            if (baseClass.getLeader()) leaderLevel = baseClass.level;
        }
        // Shouldn't happen
        if (leaderLevel == 0) throw new IllegalStateException("Something went wrong.");
        // Formula for enemies running is Morale - 2*[Leader's Level] + (0...50) < 80
        if (enemy.morale - 2 * (leaderLevel) + Math.floor(Math.random() * 50) < 80)
            associatedBattle.conclude();
    }

    // Constructors

    /**
     * Constructs an object of type Enemy with a specified name, battle, and game state.
     * Temporary implementation.
     * @param name the name given to the enemy
     * @param battle the battle associated with the enemy
     * @param gameState the game state associated with the enemy
     * @throws Exception if an error occurs assigning a battle or game state with the enemy
     */
    public Enemy(String name, Battle battle, GameState gameState) {
        this.currentName = name;
        try {
            associatedBattle = battle;
            associatedGameState = gameState;
        }
        catch (Exception e) {
            System.out.println("Error associating entered battle and game state with the class.");
        }
    }
}
