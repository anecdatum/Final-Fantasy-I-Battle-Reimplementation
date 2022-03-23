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

    /**
     * Enemy permutation of the physical attack calculations, derived from the BaseClass with some
     * modifications.
     * @param target the BaseClass being targeted by the physical attack
     * @return an int representing the sum of all physical attack values
     */
    public int physicalAttack(BaseClass target) {
        int baseChanceToHit = 168;     // for calculating the base chance to hit
        int chanceToHit;               // follows the base chance to hit
        int hitRoll;                   // determines if a hit connects
        int baseChanceToInflict = 100; // determines if a status attack element is inflicted upon
                                       // a class
        int chanceToInflict;           // follows the base chance to inflict
        Boolean applyStatus = false;   // represents if a status attack element will be inflicted
        // Update number of hits to ensure accuracy
        // calculateNumberOfHits();  Potentially remove; enemy numberOfHits is predetermined and
        // not influenced by equipment or weapons
        int[] tempPhysicalAttacks = new int[numberOfHits - 1]; // separate attacks values per hit
        for (int i = 1; i < numberOfHits; i++) {
            // If bugs are enabled, the potential to inflict a status element is separate from the
            // chance to land a successful hit, and as such is calculated here
            if (associatedGameState.getBugs()) {
                // Formula for base chance to inflict is 100, unless the target is resistant to the
                // enemy's weakness, in which case the base chance is 0
                // Determine if a resistance is present among the target's armor
                for (Armor armor : target.armor) {
                    for (String str : this.weaknesses) {
                        if (armor.element.contains(str)) baseChanceToInflict = 0;
                        break; // Only occurs once
                    }
                }
                // Formula for chance to inflict is base chance to inflict - magic defense
                chanceToInflict = baseChanceToInflict - this.magicDefense;
                // Formula for determining if a status is inflicted is a random number from 0...200
                // ; if the number is less than or equal to the chance to inflict, the status is
                // applied
                // Random number used for chance to inflict calculations
                int chanceToInflictRandomNumber = (int)Math.floor(Math.random() * 200);
                if (chanceToInflictRandomNumber <= chanceToInflict &&
                        chanceToInflictRandomNumber != 200) applyStatus = true;
                        // target.currentStatuses.add() temporary implementation
            }
            // Update hit percent to ensure accuracy
            // calculateHitPercent();  Potentially remove; enemy hitPercent is predetermined and
            // not affected by equipment or weapons
            // Calculate the Base Chance to Hit
            // If the attacker is blind, subtract 40
            if (this.currentStatuses.contains("BLIND")) baseChanceToHit -= 40;
            // If the target is blind, add 40
            if (target.currentStatuses.contains("BLIND")) baseChanceToHit += 40;
            // Calculate the Chance to Hit
            // If bugs are enabled, the formula for the Chance to Hit is (Base Chance to Hit +
            // Hit Percent) - Evasion; if the target is asleep or paralyzed, the Chance to Hit
            // equals the Base Chance to Hit + Hit Percent is capped at 255
            if (associatedGameState.getBugs()) {
                if (target.currentStatuses.contains("SLEEP") ||
                        target.currentStatuses.contains("PARALYSIS")) chanceToHit = baseChanceToHit;
                else {
                    int tempChanceToHit = baseChanceToHit + this.accuracy;
                    if (tempChanceToHit > 255) tempChanceToHit = 255;
                    chanceToHit = tempChanceToHit - this.evasion;
                }
            }
            // If bugs are not enabled, the formula for Chance to Hit is Base Chance to Hit +
            // Hit Percent - Evasion; if the target is asleep or paralyzed, the Chance to Hit
            // equals the Base Chance to Hit + Hit Percent
            else {
                if (target.currentStatuses.contains("SLEEP") ||
                        target.currentStatuses.contains("PARALYSIS"))
                    chanceToHit = baseChanceToHit + this.accuracy;
                else {
                    chanceToHit = baseChanceToHit + this.accuracy - this.evasion;
                }
            }
            // Hit Rolls are a random number selected from 0 to 200. If the hit roll is less than
            // or equal to the Chance to Hit, the hit connects. A 0 is an automatic hit, while a
            // 200 is an automatic miss.
            hitRoll = (int)Math.floor(Math.random() * 200);
            // Update critical hit rate to ensure accuracy
            // calculateCriticalHitRate(); Potentially remove; enemy criticalHitRate is
            // predetermined and not influenced by equipment and weapons
            // Determine if a critical hit is made
            // If the Hit Roll is less than or equal to the Critical Hit rate, then the hit is a
            // critical hit. 0 is an automatic critical hit, while 200 is automatically a not
            // critical
            if (hitRoll <= chanceToHit || hitRoll == 0) { // Hit goes through
                // Formula for base chance to inflict is 100, unless the target is resistant to the
                // enemy's attack element, in which case the base chance is 0
                // Determine if a resistance is present among the target's armor
                if (!applyStatus) { // Only calculate if a chance to apply has not been determined
                    for (Armor armor : target.armor) {
                        for (String str : this.statusAttackElement) {
                            if (armor.element.contains(str)) baseChanceToInflict = 0;
                            break; // Only occurs once
                        }
                    }
                    // Formula for chance to inflict is base chance to inflict - magic defense
                    chanceToInflict = baseChanceToInflict - this.magicDefense;
                    // Formula for determining if a status is inflicted is a random number from 0...200
                    // ; if the number is less than or equal to the chance to inflict, the status is
                    // applied
                    // Random number used for chance to inflict calculations
                    int chanceToInflictRandomNumber = (int) Math.floor(Math.random() * 200);
                    if (chanceToInflictRandomNumber <= chanceToInflict &&
                            chanceToInflictRandomNumber != 200) applyStatus = true;
                    // target.currentStatuses.add() temporary implementation
                }
                // Update Attack to ensure accuracy
                // this.calculateAttack(); Potentially remove; enemy attack is predetermined
                // and not influenced by equipment and weapons
                // Update Defense to ensure accuracy
                // this.calculateDefense(); Potentially remove; enemy attack is predetermined and
                // not influenced by equipment and weapons
                if (hitRoll <= this.criticalRate || hitRoll == 0) { // Perform a critical hit
                    // for the critical hit damage formula
                    int tempRandomForCriticalHit = (int)(Math.floor(Math.random() *
                            ((2 * this.attack) - this.attack))) - this.attack;
                    tempPhysicalAttacks[i] += (tempRandomForCriticalHit) +
                            (tempRandomForCriticalHit - this.defense);
                }
                else { // Critical hit failed; go for normal attack
                    // If the target is asleep or paralyzed, Attack = A * 5/4
                    if (target.currentStatuses.contains("SLEEP") ||
                            target.currentStatuses.contains("PARALYSIS"))
                        tempPhysicalAttacks[i] += (int)Math.floor(this.attack * 1.25);
                    // Formula for physical damage is Attack...2Attack - Defense
                    tempPhysicalAttacks[i] += (int)(Math.floor(Math.random() * ((2 * this.attack) - this.attack)) -
                            this.attack) - this.defense;
                }
                // Minimum damage per hit must be one
                if (tempPhysicalAttacks[i] <= 0) tempPhysicalAttacks[i] = 1;
            }
        }
        int sumOfAllPhysicalAttacks = 0;
        for (int i : tempPhysicalAttacks) {
            sumOfAllPhysicalAttacks += i;
        }
        // Potentially merely decrease the target's HP by sumOfAllPhysicalAttacks
        return sumOfAllPhysicalAttacks;
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
