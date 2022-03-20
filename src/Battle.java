import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains information and methods regarding battles.
 */
public class Battle {
    protected ArrayList<BaseClass> classes = new ArrayList<>();
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    protected int ambushDeterminant;
    protected int surpriseFactor;
    protected enum ambushStatuses {AMBUSH, NORMAL, PREEMPTIVE};
    protected String ambushStatus;
    protected Boolean runnable;
    protected ArrayList<Object> turnOrder = new ArrayList<>();

    // Methods

    /**
     * Determines the ambush determinant, which in turn determines the ambush status of the battle
     * @throws IllegalStateException if no leader is found in the classes
     */
    public void determineAmbushDeterminant() throws IllegalStateException {
        // If the battle is nonrunnable, the ambush status equals normal
        if (!runnable) this.ambushStatus = String.valueOf(ambushStatuses.NORMAL);
        Boolean foundLeader = false;   // if a leader was found within the classes in the battle
        int tempAmbushDeterminant = 0; // for intermittent ambushDeterminant calculations
        // Determine which class is the leader
        for (BaseClass pc : classes) {
            if (pc.leader) {
                // Update leader's initiative to ensure accuracy
                pc.calculateInitiative();
                // Formula for determining the ambush determinant is a random number from
                // initiative to 100 + initiative - surprise factor
                tempAmbushDeterminant = (int)(Math.floor(Math.random() * (100 - pc.initiative))
                        + pc.initiative);
                tempAmbushDeterminant += pc.initiative - this.surpriseFactor;
                foundLeader = true;
                break;
            }
        }
        if (!foundLeader) {
            // Something is incorrect; no class with leader designation found
            throw new IllegalStateException("No class found with leader designation.");
        }
        // calculateAmbushFactor(); temporary until method is implemented and classes are parsed
        // ambushDeterminant equals 0 if less than 0
        if (tempAmbushDeterminant < 0) tempAmbushDeterminant = 0;
        // Determine ambushStatus; if ambushDeterminant <= 10, ambush;
        // if 10 < ambushDeterminant < 90, normal; if ambushDeterminant >= 90, preemptive
        if (tempAmbushDeterminant <= 10) this.ambushStatus = String.valueOf(ambushStatuses.AMBUSH);
        if (10 < tempAmbushDeterminant && tempAmbushDeterminant < 90) this.ambushStatus =
                String.valueOf(ambushStatuses.NORMAL);
        else this.ambushStatus = String.valueOf(ambushStatuses.PREEMPTIVE);
    }

    // public void calculateAmbushFactor() {}

    /**
     * Calculates the turn order for the current battle's turn
     */
    public void calculateTurnOrder() {
        int classStartingIndex = 0; // for starting at the index after enemies is populated
        // for intermittent turnOrder calculations
        Object[] tempTurnOrder = new Object[classes.size() + enemies.size()];
        // Populate tempTurnOrder with enemies
        for (int i = 0; i < enemies.size(); i++) {
            tempTurnOrder[i] = enemies.get(i);
            classStartingIndex++;
        }
        // Populate tempTurnOrder with classes
        for (int i = classStartingIndex; i < classes.size(); i++) {
            tempTurnOrder[i] = classes.get(i);
        }
        // Assign turn order
        for (int i = 0; i < 17; i++) {
            int indexOne, indexTwo; // for indices of which to swap
            indexOne = (int)(Math.random() * (tempTurnOrder.length));
            indexOne = (int)Math.floor(indexOne);
            indexTwo = (int)(Math.random() * (tempTurnOrder.length));
            indexTwo = (int)Math.floor(indexTwo);
            var tempIndexOne = tempTurnOrder[indexOne];
            var tempIndexTwo = tempTurnOrder[indexTwo];
            tempTurnOrder[indexTwo] = tempIndexOne;
            tempTurnOrder[indexOne] = tempIndexTwo;
        }
        ArrayList<Object> tempTurnOrderArrayList =
                new ArrayList<>(Arrays.asList(tempTurnOrder));
        turnOrder.clear();
        turnOrder.addAll(tempTurnOrderArrayList);
    }

    /**
     * Concludes the current battle. Temporary implementation.
     */
    public void conclude() {
        System.exit(0);
    }

    // Mutator methods

    /**
     * Returns the ambush status of the current battle
     * @return the ambush status of the current battle
     */
    public String getAmbushStatus() {
        return this.ambushStatus;
    }

    /**
     * Returns the ArrayList of classes in the battle
     * @return the ArrayList of classes in the battle
     */
    public ArrayList<BaseClass> getClasses() {
        return classes;
    }

    /**
     * Returns the ArrayList of enemies in the battle
     * @return the ArrayList of enemies in the battle
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Returns the ArrayList of the turn order of the battle
     * @return the ArrayList of the turn order of the battle
     */
    public ArrayList<Object> getTurnOrder() {
        return turnOrder;
    }
}
