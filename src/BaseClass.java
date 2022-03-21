import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class which serves as a basis.
 */
public class BaseClass {
    protected Battle associatedBattle;
    protected GameState associatedGameState;
    protected ArrayList<String> classTypes = new ArrayList<>(); // Names for classes

    {
        classTypes.add("FIGHTER");
        classTypes.add("KNIGHT");
        classTypes.add("THIEF");
        classTypes.add("NINJA");
        classTypes.add("BLACK_BELT");
        classTypes.add("MASTER");
        classTypes.add("RED_MAGE");
        classTypes.add("RED_WIZARD");
        classTypes.add("WHITE_MAGE");
        classTypes.add("WHITE_WIZARD");
        classTypes.add("BLACK_MAGE");
        classTypes.add("BLACK_WIZARD");
    }

    // Character stats

    protected String currentClassType;
    protected int level;
    protected int XP;
    protected int HP;
    protected int MP;
    protected int strength;
    protected int agility;
    protected int intelligence;
    protected int vitality;
    protected int luck;
    protected int attack;
    protected int defense;
    protected int hitPercent;
    protected int evadePercent;
    protected int magicDefense;
    protected int criticalHitRate;

    // Equipment and spell stats

    protected Weapon weapon;
    protected ArrayList<Armor> armor = new ArrayList<>();
    protected ArrayList<Magic.Spell> spells = new ArrayList<>();

    // Battle stats

    protected Boolean leader;
    protected int initiative;
    protected int numberOfHits;
    protected ArrayList<String> currentAfflictedSpells = new ArrayList<>();
    protected HashMap<String, Integer> statusByteEquivalent = new HashMap<>();
    {
        statusByteEquivalent.put("DEAD", 1);
        statusByteEquivalent.put("PETRIFIED", 2);
        statusByteEquivalent.put("POISONED", 4);
        statusByteEquivalent.put("BLIND", 8);
        statusByteEquivalent.put("PARALYZED", 10);
        statusByteEquivalent.put("ASLEEP", 20);
        statusByteEquivalent.put("SILENCED", 40);
        statusByteEquivalent.put("CONFUSED", 80);

    }
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

    // Player statistic calculation formulas

    /**
     * Calculates the player's attack.
     */
    public void calculateAttack() {
        int tempAttack = 0; // for intermittent attack calculations
        // If the class type is a black belt, master, black mage, or black wizard, and the
        // player is armed, formula is attack = weapon attack + strength/2 + 1
        if ((currentClassType.equals("BLACK_BELT") || currentClassType.equals("MASTER") ||
                currentClassType.equals("BLACK_MAGE") || currentClassType.equals("BLACK_WIZARD"))
            && (weapon != null)) {
            tempAttack = weapon.weaponAttack + strength/2 + 1;
        }
        // If the class type is a black belt, master, black mage, or black wizard, and player
        // is unarmed, formula is level*2
        else if ((currentClassType.equals("BLACK_BELT") || currentClassType.equals("MASTER") ||
                currentClassType.equals("BLACK_MAGE") || currentClassType.equals("BLACK_WIZARD"))
                && (weapon == null)) {
            tempAttack = level * 2;
        }
        // Perform standard attack, of which the formula is weapon attack + str/2
        else {
            tempAttack = weapon.weaponAttack + strength/2;
        }

        // Determine if attack overflows
        if (tempAttack > 255) {
            this.attack = tempAttack % 255;
        }
        else this.attack = tempAttack;
    }

    /**
     * Calculates the player's defense.
     */
    public void calculateDefense() {
        int tempDefense = 0; // for intermittent defense calculations
        // If the class type is black belt or master, and the player does not have equipment,
        // the formula is defense = level*2
        if ((currentClassType.equals("BLACK_BELT") || currentClassType.equals("MASTER")) &&
            weapon == null) {
            this.defense = level * 2;
        }
        // Perform standard defense calculation, of which the formula is defense = sum of
        // equipped armor
        for (Armor armor : armor) {
            tempDefense += armor.armorDefense;
        }
        this.defense = tempDefense;
    }

    /**
     * Calculates the player's hit percent.
     */
    public void calculateHitPercent() {
        this.hitPercent = weapon.weaponAccuracy * hitPercent;
    }

    /**
     * Calculates the player's evade percent.
     */
    public void calculateEvadePercent() {
        int tempEvadePercent = 0; // for intermittent evadePercent calculations
        for (Armor armor : armor) {
            tempEvadePercent += armor.weight;
        }
        this.evadePercent = 48 + agility - tempEvadePercent;
    }

    /**
     * Calculates the player's number of hits.
     */
    public void calculateNumberOfHits() {
        int tempNumberOfHits = 0; // for intermittent NumberOfHits calculations
        int hitMultiplier = 1;    // Modifies number of hits dependent upon certain criteria
        if (currentAfflictedSpells.contains("SLOW")) hitMultiplier -= 1;
        if (currentAfflictedSpells.contains("FAST")) hitMultiplier += 1;
        // If the class type is black belt or master, and the player is unarmed, the formula
        // is number of hits = 2([1+(hitPercent/32)]*Hit Multiplier)
        if ((currentClassType.equals("BLACK_BELT") || currentClassType.equals("MASTER")) &&
            weapon == null) {
            tempNumberOfHits = 2 * ((1 + (hitPercent / 32)) * hitMultiplier);
        }
        // Perform standard number of hits calculation, of which the formula is
        // [1+(hitPercent/32)*Hit Multiplier
        else tempNumberOfHits = (1 + (hitPercent / 32)) * hitMultiplier;

        // Minimum number of hits must be one
        if (tempNumberOfHits <= 0) tempNumberOfHits = 1;
        this.numberOfHits = tempNumberOfHits;
    }

    /**
     * Calculates the player's critical hit rate.
     */
    public void calculateCriticalHitRate() {
        // If the class type is black belt or master, and the player is unarmed, the formula is
        // critical rate = level*2
        if ((currentClassType.equals("BLACK_BELT") || currentClassType.equals("MASTER")) &&
            this.weapon == null) {
            this.criticalHitRate = level * 2;
        }
        // If the class type is not a black belt or a master, and the player is unarmed, the
        // formula is critical rate = 0
        else if (this.weapon == null) {
            this.criticalHitRate = 0;
        }
        // Perform standard critical hit rate calculation, of which the formula is
        // critical hit rate = weapon index number
        this.criticalHitRate = this.weapon.index;
    }

    /**
     * Calculates the class' initiative.
     */
    public void calculateInitiative() {
        // Formula for calculating initiative is (leader's agility + leader's luck) / 8
        this.initiative = (this.agility + this.luck) / 8;
    }

    // Battle formulas

    /**
     * Initiates an attempt to run from the current battle.
     * @param baseClass Class which attempts to run
     * @throws IllegalArgumentException occurs if something occurs with obtaining battle classes
     * and turn orders
     * @throws Exception occurs if a general error happens during the assignment of the status byte
     */
    public void run(BaseClass baseClass) throws IllegalArgumentException, Exception {
        BaseClass baseClassComparison;  // used to compared against the entered baseClass
        int baseClassIndex;             // integer for index of baseClass in the battle classes
        int levelSubstitute;            // integer used for running calculations when bugs are
                                        // enabled
        // If the battle is preemptive, running is always successful
        if (associatedBattle.getAmbushStatus().equals("PREEMPTIVE")) associatedBattle.conclude();
        // If the battle is unrunnable, running is never successful
        if (!associatedBattle.runnable) return;
        /* If bugs are enabled, use broken running formula, which is depends on the following
        criteria:
        Slot 1: Slot 3's status
        Slot 2: Slot 4's status
        Slot 3: Identifier value for third in turn order
        Slot 4: First digit of Slot 4's HP
         */
        ArrayList<BaseClass> tempClasses = associatedBattle.getClasses();
        ArrayList<Object> tempTurnOrder = associatedBattle.getTurnOrder();
        if (tempClasses.contains(baseClass)) {
            baseClassComparison = tempClasses.get(tempClasses.indexOf(baseClass));
            baseClassIndex = tempClasses.indexOf(baseClass);
        }
        // Shouldn't happen
        else throw new IllegalArgumentException("Something went wrong.");
        //
        switch (baseClassIndex) {
            case 0:
                try {
                    // Obtain the status byte of Slot 3
                    ArrayList<String> classThreeStatuses = tempClasses.get(2).currentStatuses;
                    if (classThreeStatuses.isEmpty()) levelSubstitute = 0;
                    else {
                        // Slot 3 has a separate status; assign the appropriate status byte to
                        // levelSubstitute
                        for (String str : classThreeStatuses) {
                            // The last status afflicted will be assigned
                            if (statusByteEquivalent.containsKey(str)) {
                                levelSubstitute = statusByteEquivalent.get(str);
                            }
                        }
                    }
                }
                // Shouldn't occur
                catch (Exception e) {
                    throw new Exception("Something went wrong.");
                }
            case 1:
                // Get numerical status of slot 4
                try {
                    // Obtain the status byte of Slot 4
                    ArrayList<String> classFourStatuses = tempClasses.get(3).currentStatuses;
                    if (classFourStatuses.isEmpty()) levelSubstitute = 0;
                    else {
                        // Slot 3 has a separate status; assign the appropriate status byte to
                        // levelSubstitute
                        for (String str : classFourStatuses) {
                            // The last status afflicted will be assigned
                            if (statusByteEquivalent.containsKey(str)) {
                                levelSubstitute = statusByteEquivalent.get(str);
                            }
                        }
                    }
                }
                // Shouldn't occur
                catch (Exception e) {
                    throw new Exception("Something went wrong.");
                }
            case 2:
                // LevelSubstitute equals 3 for the identifier of the third entity; this is due to
                // the potential for additional classes and enemies not possible in the original
                // game
                levelSubstitute = 2;
            default:
                // Get the one digit of the HP of slot 4
                levelSubstitute = tempClasses.get(3).HP;
        }
        // Compute formula Luck > 0...[Level + 15]
        // Use different non-bugged formula if bugs disabled
        if (associatedGameState.getBugs()) {
            // If Luck > 0...[levelSubstitute + 15], conclude battle
            if (levelSubstitute > Math.floor(Math.random() * ((this.level + 15))))
                associatedBattle.conclude();
        }
        else {
            if (this.luck > Math.floor(Math.random() * ((this.level + 15))))
                associatedBattle.conclude();
        }
    }

    /**
     * Calculates the physical attack by a class
     * @param target the enemy being targeted by the physical attack
     * @return an int representing the sum of all hits performed
     */
    public int physicalAttack(Enemy target) {
        int baseChanceToHit = 168;  // for calculating the base chance to hit
        int chanceToHit;            // follows the base chance to hit
        int hitRoll;                // determines if a hit connects
        // Update number of hits to ensure accuracy
        calculateNumberOfHits();
        int[] tempPhysicalAttacks = new int[numberOfHits - 1]; // separate attacks values per hit
        for (int i = 1; i < numberOfHits; i++) {
            // Update hit percent to ensure accuracy
            calculateHitPercent();
            // Calculate the Base Chance to Hit
            // If the attacker is blind, subtract 40
            if (this.currentStatuses.contains("BLIND")) baseChanceToHit -= 40;
            // If the target is blind, add 40
            if (target.currentStatuses.contains("BLIND")) baseChanceToHit += 40;
            // If bugs are not enabled, if a weapon attribute matches an elemental weakness or enemy
            // type of an enemy, add 40
            if (!associatedGameState.getBugs()) {
                // Determine if a weapon attribute matches an elemental weakness
                for (String str : this.weapon.currentElements) {
                    if (target.weaknesses.contains(str)) baseChanceToHit += 40;
                    break; // Only occurs once
                }
                // Determine if a weapon attribute matches an enemy type
                for (String str : this.weapon.currentEnemyFamilyTypes) {
                    if (target.currentEnemyFamilyTypes.contains(str)) baseChanceToHit += 40;
                    break; // Only occurs once
                }
            }
            // Calculate the Chance to Hit
            // If bugs are enabled, the formula for the Chance to Hit is (Base Chance to Hit +
            // Hit Percent) - Evasion; if the target is asleep or paralyzed, the Chance to Hit
            // equals the Base Chance to Hit + Hit Percent is capped at 255
            if (associatedGameState.getBugs()) {
                if (target.currentStatuses.contains("SLEEP") ||
                        target.currentStatuses.contains("PARALYSIS")) chanceToHit = baseChanceToHit;
                else {
                    int tempChanceToHit = baseChanceToHit + this.hitPercent;
                    if (tempChanceToHit > 255) tempChanceToHit = 255;
                    chanceToHit = tempChanceToHit - this.evadePercent;
                }
            }
            // If bugs are not enabled, the formula for Chance to Hit is Base Chance to Hit +
            // Hit Percent - Evasion; if the target is asleep or paralyzed, the Chance to Hit
            // equals the Base Chance to Hit + Hit Percent
            else {
                if (target.currentStatuses.contains("SLEEP") ||
                        target.currentStatuses.contains("PARALYSIS"))
                    chanceToHit = baseChanceToHit + this.hitPercent;
                else {
                    chanceToHit = baseChanceToHit + this.hitPercent - this.evadePercent;
                }
            }
            // Hit Rolls are a random number selected from 0 to 200. If the hit roll is less than
            // or equal to the Chance to Hit, the hit connects. A 0 is an automatic hit, while a
            // 200 is an automatic miss.
            hitRoll = (int)Math.floor(Math.random() * 200);
            // Update critical hit rate to ensure accuracy
            calculateCriticalHitRate();
            // Determine if a critical hit is made
            // If the Hit Roll is less than or equal to the Critical Hit rate, then the hit is a
            // critical hit. 0 is an automatic critical hit, while 200 is automatically a not
            // critical
            if (hitRoll <= chanceToHit || hitRoll == 0) { // Hit goes through
                // Update Attack to ensure accuracy
                this.calculateAttack();
                // Update Defense to ensure accuracy
                this.calculateDefense();
                if (hitRoll <= this.criticalHitRate || hitRoll == 0) { // Perform a critical hit
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
                    // If bugs are not enabled and the target is weak to an Elemental or Enemy-Type attribute
                    // of a weapon, add 4 to the Attack
                    if (!associatedGameState.getBugs()) {
                        // Determine if the target is weak to the element
                        for (String str : target.weaknesses) {
                            if (this.weapon.currentElements.contains(str)) {
                                tempPhysicalAttacks[i] += 4;
                                break; // Only occurs once
                            }
                        }
                    }
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

    public void useMagic(String str) {
        // if (
    }


    // Constructors

    /**
     * Constructs a class with a specified class type.
     * @param str Type name of a legal class
     * @param battle Battle of which the class is participating in
     * @param gameState Game state of which the clas is participating in
     * @throws IllegalArgumentException if the entered class is not a valid class
     * @throws Exception if there is an error assigning a battle or game state to a class
     */
    public BaseClass(String str, Battle battle, GameState gameState) throws IllegalArgumentException {
        if (classTypes.contains(str.toUpperCase())) {
            this.currentClassType = str.toUpperCase();
        }
        else throw new IllegalArgumentException("Non-existent class entered.");
        try {
            associatedBattle = battle;
            associatedGameState = gameState;
        }
        catch (Exception e) {
            System.out.println("Error associating entered battle and game state with the class.");
        }
    }

    // Mutator methods

    /**
     * Sets the class to a leader or not.
     * @param leader A boolean determining whether the class is to be the leader.
     */
    public void setLeader(Boolean leader) {
        this.leader = leader;
    }

    /**
     * Returns the boolean status of the class, if the class is the leader or not.
     * @return Boolean representing the leader status of the class
     */
    public Boolean getLeader() {
        return leader;
    }

    // Temporary implementation public void setSingleArmor(Armor armor) {}

    /**
     * Sets the armor of a class. Temporary implementation.
     * @param armor an ArrayList of specified armor to replace the current armor
     */
    public void setArmor(ArrayList<Armor> armor) {
        this.armor.clear();
        this.armor.addAll(armor);
    }

    /**
     * Sets the weapon of a class. Temporary implementation.
     * @param weapon a weapon to assign the class
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
