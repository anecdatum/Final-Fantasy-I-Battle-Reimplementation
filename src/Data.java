/**
 * Contains data for objects such as for weapons, spells, enemies, and others.
 * There are likely better ways of doing this.
 */
public class Data {
    public static class BaseData extends Data {
            final protected Object[][] NESWeaponData =
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

            final protected Object[][] NESArmorData =
                    {{"Index", "Armor", "Armor Type", "Defense", "Weight", "Resist", "Spell", "Price", "Equippable By"}};

            final protected Object[][][] NESWeaponCriticalHitChanceTable =
                    {{{"Index", "Chance of at Least 1 Critical Hit", "Chance of specified number of Critical Hits"}}};

            final protected Object[][] NESSpellData =
                    {{"Index", "Name", "Effectivity", "Accuracy", "Element", "Target", "Effect", "Price", "Usable Classes"}};

            final protected Object[][] NESItemData = {{"Index", "Name", "Effectivity", "Accuracy", "Target", "Effect"}};

            final protected Object[][] NESSkillData = {{"Index", "Name", "Effectivity", "Accuracy", "Element", "Target", "Effect"}};

            final protected Object[][] NESGroupSurpriseFactorsData =
                    {{"Index", "Composition", "Surprise Factor"}};

            final protected Object[][] NESUnrunnableBattleData = {{"Index", "Composition"}};

            final protected Object[][] NESEnemyData =
                    {{"Index", "Name", "HP", "Attack", "Accuracy", "Number of Hits", "Critical Rate", "Defense", "Evasion",
                      "Magic Defense", "Morale", "Status Attack", "Status Attack Element", "Enemy Family", "Weaknesses",
                      "Resistances", "Magic", "Special", "Gold", "Experience", "Formations", "Locations", "AI"}};

            final protected Object[][] NESPatternTableData = {{"Index", "Composition"}};

            final protected Object[][] NESEnemyFormationList = {{}};
        }
    }