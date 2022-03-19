import java.util.ArrayList;

public class PlayerClass {

    /**
     * Class which serves as a basis.
     */
    public class BaseClass {
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
        protected ArrayList<Spell> spells = new ArrayList<>();

        // Battle stats

        protected int numberOfHits;
        protected ArrayList<String> currentAfflictedSpells = new ArrayList<>();
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
        // Potentially add current status(es)

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
                weapon == null) {
                this.criticalHitRate = level * 2;
            }
            // If the class type is not a black belt or a master, and the player is unarmed, the
            // formula is critical rate = 0
            else if (weapon == null) {
                this.criticalHitRate = 0;
            }
            // Perform standard critical hit rate calculation, of which the formula is
            // critical hit rate = weapon index number
            this.criticalHitRate = weapon.index;
        }

        // Constructors

        /**
         * Constructs a class with a specified class type.
         * @param str Type name of a legal class
         * @throws IllegalArgumentException If the entered class is not a valid class
         */
        public BaseClass(String str) throws IllegalArgumentException {
            if (classTypes.contains(str.toUpperCase())) {
                this.currentClassType = str.toUpperCase();
            }
            else throw new IllegalArgumentException("Non-existent class entered.");
        }

    }
}
