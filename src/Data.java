import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Contains data for objects such as for weapons, spells, enemies, and others.
 * There are likely better ways of doing this.
 */
public class Data {
    public static class BaseData extends Data {
            static final protected Object[][] NESWeaponData =
                    {{"Index", "Weapon", "Weapon Type", "Attack", "Hit Percent",
                    "Critical", "Type", "Spell", "Price", "Equippable By"},
                    {1, "Wooden", "Nunchucks", 12, 0, 10, null, null, 10, "NINJA RED_MAGE"},
                    {2, "Small", "Knife", 5, 10, 5, null, null, 5, "FIGHTER THIEF RED_MAGE BLACK_MAGE"},
                    {3, "Wooden", "Staff/Rod", 6, 0, 1, null, null, 5, "FIGHTER NINJA BLACK_BELT RED_MAGE WHITE_MAGE BLACK_MAGE"},
                    {4, "Rapier", null, 9, 0, 1, null, null, 10, "FIGHTER THIEF RED_MAGE"},
                    {5, "Iron", "Hammer", 9, 0, 1, null, null, 10, "FIGHTER NINJA WHITE_MAGE"},
                    {6, "Short", "Sword", 15, 10, 5, null, null, 550, "FIGHTER NINJA RED_MAGE"},
                    {7, "Hand", "Axe", 16, 5, 3, null, null, 550, "FIGHTER NINJA"},
                    {8, "Scimtar", null, 10, 10, 5, null, null, 200, "FIGHTER THIEF RED_MAGE"},
                    {9, "Iron", "Nunchucks", 16, 0, 10, null, null, 200, "NINJA BLACK_BELT"},
                    {10, "Large", "Knife", 7, 10, 5, null, null, 175, "FIGHTER THIEF RED_MAGE BLACK_MAGE"},
                    {11, "Iron", "Sword", 14, 0, 1, null, null, 200 ,"FIGHTER NINJA BLACK_BELT"},
                    {12, "Sabre", null, 13, 5, 10, null, null, 450, "FIGHTER THIEF RED_MAGE"},
                    {13, "Long", "Sword", 20, 10, 5, null, null, 450, "FIGHTER NINJA RED_MAGE"},
                    {14 ,"Great", "Axe", 22, 5, 3, null, null, 2000, "FIGHTER NINJA"},
                    {15, "Falchon", null, 15, 10, 5, null, null, 450, "FIGHTER THIEF RED_MAGE"},
                    {16, "Silver", "Knife", 10, 15, 5, null, null, 800, "FIGHTER THIEF RED_MAGE BLACK_MAGE"},
                    {17, "Silver", "Sword", 23, 15, 5, null, null, 4000, "FIGHTER NINJA RED_MAGE"},
                    {18, "Silver", "Hammer", 12, 5, 1, null, null, 2500, "FIGHTER NINJA WHITE_MAGE"},
                    {19, "Silver", "Axe", 25, 10, 4, null, null, 4500, "FIGHTER NINJA"},
                    {20, "Flame", "Sword", 26, 20, 5, "FIRE UNDEAD REGENERATIVE", null, 10000, "FIGHTER NINJA RED_MAGE"},
                    {21, "Ice", "Sword", 29, 25, 5, "ICE", null, 15000, "FIGHTER NINJA RED_MAGE"},
                    {22, "Dragon", "Sword", 19, 15, 10, "DRAGON", null, 8000, "FIGHTER NINJA RED_MAGE"},
                    {23, "Giant", "Sword", 21, 20, 5, "GIANT", null, 8000, "FIGHTER THIEF RED_MAGE"},
                    {24, "Sun", "Sword", 32, 30, 5, "UNDEAD", null, 20000, "FIGHTER THIEF RED_MAGE"},
                    {25, "Coral", "Sword", 19, 15, 10, "AQUATIC", null, 8000, "FIGHTER NINJA RED_MAGE"},
                    {26, "Were", "Sword", 18, 15, 5, "WERE", null, 6000, "FIGHTER NINJA RED_MAGE"},
                    {27, "Rune", "Sword", 18, 15, 5, "MAGICAL MAGE", null, 5000, "FIGHTER THIEF RED_MAGE"},
                    {28, "Power", "Staff/Rod", 12, 0, 1, null, null, 12345, "FIGHTER NINJA BLACK_BELT WHITE_MAGE RED_MAGE"},
                    {29, "Light", "Axe", 28, 15, 3, "UNDEAD", "HRM2", 10000, "FIGHTER NINJA"},
                    {30, "Heal", "Staff/Rod", 6, 0, 1, null, "HEAL", 25000, "NINJA WHITE_MAGE"},
                    {31, "Mage", "Staff/Rod", 12, 10, 1, null, "FIR2", 25000, "NINJA BLACK_MAGE"},
                    {32, "Defense", null, 30, 35, 5, null, "RUSE", 40000, "KNIGHT NINJA RED_MAGE"},
                    {33, "Wizard", "Staff/Rod", 15, 15, 1, null, "CONF", 50000, "BLACK_MAGE"},
                    {34, "Vorpal", null, 24, 25, 30, null, null, 30000, "KNIGHT NINJA RED_MAGE "},
                    {35, "CatClaw", null, 22, 35, 5, null, null, 65000, "KNIGHT NINJA RED_MAGE BLACK_MAGE"},
                    {36, "Thor", "Hammer", 18, 15, 1, null, "LIT2", 40000, "KNIGHT NINJA WHITE_WIZARD"},
                    {37, "Bane", "Sword", 22, 20, 10, null, "BANE", 60000, "KNIGHT NINJA RED_WIZARD"},
                    {38, "Katana", null, 33, 35, 30, null, null, 60000, "NINJA"},
                    {39, "Xcalber", null, 45, 35, 5, "ALL", null, 60000, "KNIGHT"},
                    {40, "Masmune", null, 56, 50, 10, null, null, 60000, "FIGHTER THIEF BLACK_BELT RED_MAGE WHITE_MAGE BLACK_MAGE"}};

            static final protected Object[][] NESArmorData =
                    {{"Index", "Armor", "Armor Type", "Defense", "Weight", "Resist", "Spell", "Price", "Equippable By"},
                     {1, "Cloth", null, 1, 2, null, null, 10, "FIGHTER THIEF BLACK_BELT RED_MAGE"},
                     {2, "Wooden", "Armor", 4, 8, null, null, 50, "FIGHTER THIEF BLACK_BELT RED_MAGE"},
                     {3, "Chain", "Armor", 15, 15, null, null, 80, "FIGHTER NINJA RED_MAGE"},
                     {4, "Iron", "Armor", 24, 23, null, null, 800, "FIGHTER NINJA"},
                     {5, "Steel", "Armor", 34, 33, null, null, 45000, "FIGHTER"},
                     {6, "Silver", "Armor", 18, 8, null, null, 7500, "FIGHTER NINJA RED_MAGE"},
                     {7, "Flame", "Armor", 34, 10, "ICE", null, 30000, "FIGHTER NINJA"},
                     {8, "Ice", "Armor", 34, 10, "FIRE", null, 30000, "FIGHTER NINJA"},
                     {9, "Opal", "Armor", 42, 10, "LIGHTNING", null, 60000, "KNIGHT"},
                     {10, "Dragon", "Armor", 42, 10, "FIRE ICE LIGHTNING", null, 60000, "KNIGHT"},
                     {11, "Copper", "Bracelet", 4, 1, null, null, 100, "FIGHTER THIEF BLACK_BELT RED_MAGE"},
                     {12, "Silver", "Bracelet", 15, 1, null, null, 5000, "FIGHTER THIEF BLACK_BELT RED_MAGE"},
                     {13, "Gold", "Bracelet", 24, 1, null, null, 50000, "FIGHTER THIEF BLACK_BELT RED_MAGE"},
                     {14, "Opal", "Bracelet", 34, 1, null, null, 65000, "FIGHTER THIEF BLACK_BELT RED_MAGE"},
                     {15, "White", "Robe", 24, 2, "FIRE DEATH", "INV2", 2, null},
                     {16, "Black", "Robe", 24, 2, "ICE TIME", "ICE2", 2, null},
                     {17, "Wooden", "Shield", 2, 0, null, null, 15, "FIGHTER NINJA"},
                     {18, "Iron", "Shield", 4, 0, null, null, 100, "FIGHTER NINJA"},
                     {19, "Silver", "Shield", 8, 0, null, null, 2500, "FIGHTER NINJA"},
                     {20, "Flame", "Shield", 12, 0, "ICE", null, 10000, "FIGHTER NINJA"},
                     {21, "Ice", "Shield", 12, 0, "FIRE", null, 10000, "FIGHTER NINJA"},
                     {22, "Opal", "Shield", 16, 0, "LIGHTNING", null, 15000, "KNIGHT"},
                     {23, "Aegis", "Shield", 16, 0, "POISON", null, 40000, "KNIGHT"},
                     {24, "Buckler", null, 2, 0, null, null, 2500, "FIGHTER THIEF RED_MAGE"},
                     {25, "ProCape", null, 8, 2, null, null, 20000, "FIGHTER THIEF RED_MAGE"},
                     {26, "Cap", 1, 1, null, null, 80, "FIGHTER THIEF BLACK_BELT RED_MAGE"},
                     {27, "Wooden", "Helmet", 3, 3, null, null, 100, "FIGHTER NINJA"},
                     {28, "Iron", "Helmet", 5, 5, null, null, 450, "FIGHTER NINJA"},
                     {29, "Silver", "Helmet", 6, 3, null, null, 2500, "FIGHTER NINJA"},
                     {30, "Opal", "Helmet", 8, 3, null, null, 10000, "KNIGHT"},
                     {31, "Heal", "Helmet", 6, 3, null, "HEAL", 20000, "KNIGHT NINJA"},
                     {32, "Ribbon", 1, 1, "ALL", null, 2, "FIGHTER THIEF BLACK_BELT RED_MAGE"},
                     {33, "Gloves", 1, 1, null, null, 60, "FIGHTER THIEF BLACK_BELT RED_MAGE"},
                     {34, "Copper", "Gauntlet", 2, 3, null, null, 200, "FIGHTER NINJA"},
                     {35, "Iron", "Gauntlet", 4, 5, null, null, 750, "FIGHTER NINJA"},
                     {36, "Silver", "Gauntlet", 6, 3, null, null, 2500, "FIGHTER NINJA RED_WIZARD"},
                     {37, "Zeus", "Gauntlet", 6, 3, null, "LIT2", 15000, "KNIGHT NINJA RED_WIZARD"},
                     {38, "Power", "Gauntlet", 6, 3, null, "SABR", 10000, "FIGHTER NINJA RED_WIZARD"},
                     {39, "Opal", "Gauntlet", 8 , 3, null, null, 20000, "KNIGHT"},
                     {40, "ProRing", null, 8, 1, "DEATH", null, 20000, "FIGHTER THIEF BLACK_BELT RED_MAGE"}};

            static final protected Object[][][] NESWeaponCriticalHitChanceTable =
                    {{{"Index", "Name", "Hit Number Potential", "Chance of specified number of Critical Hits", "Probability"}}};

        static final protected Object[][] NESSpellData =
                    {{"Index", "Name", "Effectivity", "Accuracy", "Element", "Target", "Effect", "Price", "Usable Classes"}};

            static final protected Object[][] NESItemData = {{"Index", "Name", "Effectivity", "Accuracy", "Target", "Effect"}};

            static final protected Object[][] NESSkillData = {{"Index", "Name", "Effectivity", "Accuracy", "Element", "Target", "Effect"}};

            static final protected Object[][] NESGroupSurpriseFactorsData =
                    {{"Index", "Composition", "Surprise Factor"}};

            static final protected Object[][] NESUnrunnableBattleData = {{"Index", "Composition"}};

            static final protected Object[][] NESEnemyData =
                    {{"Index", "Name", "HP", "Attack", "Accuracy", "Number of Hits", "Critical Rate", "Defense", "Evasion",
                      "Magic Defense", "Morale", "Status Attack", "Status Attack Element", "Enemy Family", "Weaknesses",
                      "Resistances", "Magic", "Special", "Gold", "Experience", "Formations", "Locations", "AI"}};

            static final protected Object[][] NESPatternTableData = {{"Index", "Composition"}};

            static final protected Object[][] NESEnemyFormationList = {{}};
        }
    }