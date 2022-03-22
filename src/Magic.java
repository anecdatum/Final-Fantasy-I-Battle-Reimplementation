import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains variables and methods pertaining to generalized Magic.
 */
public class Magic {
    protected String name;
    protected int baseChanceToHit = 148;
    protected enum effectRoutineTypes {NOTHING, DAMAGE, UNDEAD_DAMAGE, STATUS_AILMENT,
                                       HIT_MULTIPLIER_DOWN, MORALE_DOWN, UNUSED, HP_RECOVERY,
                                       RESTORE_STATUS, DEFENSE_UP, RESIST_ELEMENT, ATTACK_UP,
                                       HIT_MULTIPLIER_UP, ATTACK_ACCURACY_UP, EVASION_DOWN,
                                       FULL_HP_STATUS_RECOVERY, EVASION_UP, REMOVE_RESISTANCE,
                                       THREE_HUNDRED_HP_STATUS}
    protected HashMap<effectRoutineTypes, String> effectRoutines = new HashMap<>();
    {
        effectRoutines.put(effectRoutineTypes.NOTHING, "00");
        effectRoutines.put(effectRoutineTypes.DAMAGE, "01");
        effectRoutines.put(effectRoutineTypes.UNDEAD_DAMAGE, "02");
        effectRoutines.put(effectRoutineTypes.STATUS_AILMENT, "03");
        effectRoutines.put(effectRoutineTypes.HIT_MULTIPLIER_DOWN, "04");
        effectRoutines.put(effectRoutineTypes.MORALE_DOWN, "05");
        effectRoutines.put(effectRoutineTypes.UNUSED, "06");
        effectRoutines.put(effectRoutineTypes.HP_RECOVERY, "07");
        effectRoutines.put(effectRoutineTypes.RESTORE_STATUS, "08");
        effectRoutines.put(effectRoutineTypes.DEFENSE_UP, "09");
        effectRoutines.put(effectRoutineTypes.RESIST_ELEMENT, "0A");
        effectRoutines.put(effectRoutineTypes.ATTACK_UP, "0B");
        effectRoutines.put(effectRoutineTypes.HIT_MULTIPLIER_UP, "0C");
        effectRoutines.put(effectRoutineTypes.ATTACK_ACCURACY_UP, "0D");
        effectRoutines.put(effectRoutineTypes.EVASION_DOWN, "0E");
        effectRoutines.put(effectRoutineTypes.FULL_HP_STATUS_RECOVERY, "0F");
        effectRoutines.put(effectRoutineTypes.EVASION_UP, "10");
        effectRoutines.put(effectRoutineTypes.REMOVE_RESISTANCE, "11");
        effectRoutines.put(effectRoutineTypes.THREE_HUNDRED_HP_STATUS, "12");
    }
    protected ArrayList<effectRoutineTypes> currentEffectRoutines = new ArrayList<>();
    protected HashMap<String, Integer> statusEffectivityEquivalent = new HashMap<>();
    {
        statusEffectivityEquivalent.put("DEAD", 1);
        statusEffectivityEquivalent.put("PETRIFIED", 2);
        statusEffectivityEquivalent.put("POISONED", 4);
        statusEffectivityEquivalent.put("BLIND", 8);
        statusEffectivityEquivalent.put("PARALYZED", 10);
        statusEffectivityEquivalent.put("ASLEEP", 20);
        statusEffectivityEquivalent.put("SILENCED", 40);
        statusEffectivityEquivalent.put("CONFUSED", 80);

    }
    protected int effectivity;
    protected int accuracy;
    protected enum targetTypes {SINGLE_TARGET, SINGLE_ALLY, SINGLE_ENEMY, ALL_TARGETS, ALL_ALLIES,
                                ALL_ENEMIES, CASTER}
    protected ArrayList<targetTypes> currentTargetTypes = new ArrayList<>();
    /**
     * Contains variables and methods pertaining to Spells, best described as a subclass of Magic.
     */
    public class Spell extends Magic {
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
        protected int currentElement;
        protected ArrayList<String> usableClasses = new ArrayList<>();
        {
            usableClasses.add("FIGHTER");
            usableClasses.add("KNIGHT");
            usableClasses.add("THIEF");
            usableClasses.add("NINJA");
            usableClasses.add("BLACK_BELT");
            usableClasses.add("MASTER");
            usableClasses.add("RED_MAGE");
            usableClasses.add("RED_WIZARD");
            usableClasses.add("WHITE_MAGE");
            usableClasses.add("WHITE_WIZARD");
            usableClasses.add("BLACK_MAGE");
            usableClasses.add("BLACK_WIZARD");
        }
        protected ArrayList<String> currentUsableClasses = new ArrayList<>();
    }

    /**
     * Contains varibles and methods pertaining to Skills, best described as a subclass of Magic.
     */
    public class Skill extends Magic {
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
    }
}
