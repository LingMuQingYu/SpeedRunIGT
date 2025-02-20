package com.redlimerl.speedrunigt.timer.category;

import com.redlimerl.speedrunigt.timer.InGameTimer;

public class RunCategories {

    public static RunCategory ERROR_CATEGORY = new RunCategory("unknown","mc");

    public static RunCategory ANY = new RunCategory("ANY","mc#Any_Glitchless");
    public static RunCategory CUSTOM = new RunCategory("CUSTOM","mc#");
    public static RunCategory HIGH = new RunCategory("HIGH","mcce#High");
    public static RunCategory KILL_ALL_BOSSES = new RunCategory("KILL_ALL_BOSSES","mcce#Kill_Bosses");
    public static RunCategory KILL_WITHER = new RunCategory("KILL_WITHER","mcce#Kill_Bosses");
    public static RunCategory KILL_ELDER_GUARDIAN = new RunCategory("KILL_ELDER_GUARDIAN","mcce#Kill_Bosses");
    public static RunCategory ALL_ADVANCEMENTS = new RunCategory("ALL_ADVANCEMENTS","mc#All_Advancements");
    public static RunCategory HALF = new RunCategory("HALF","mcce#Half");
    public static RunCategory POGLOOT_QUATER = new RunCategory("POGLOOT_QUATER","pogloot_ce#Quater");
    public static RunCategory HOW_DID_WE_GET_HERE = new RunCategory("HOW_DID_WE_GET_HERE","mcce#How_Did_We_Get_Here");
    public static RunCategory HERO_OF_VILLAGE = new RunCategory("HERO_OF_VILLAGE","mcce#Hero_of_the_Village");
    public static RunCategory ARBALISTIC = new RunCategory("ARBALISTIC","mcce#Arbalistic");
    public static RunCategory COVER_ME_IN_DEBRIS = new RunCategory("COVER_ME_IN_DEBRIS","mcce#Cover_Me_in_Debris", "advancements.nether.netherite_armor.title");
    public static RunCategory ENTER_NETHER = new RunCategory("ENTER_NETHER","mcce#Enter_Nether");
    public static RunCategory ENTER_END = new RunCategory("ENTER_END","mcce#Etner_Edn");
    public static RunCategory ALL_SWORDS = new RunCategory("ALL_SWORDS","mcce#All_Swords");
    public static RunCategory ALL_MINERALS = new RunCategory("ALL_MINERALS","mcce#All_Minerals");
    public static RunCategory FULL_IA_15_LVL = new RunCategory("FULL_IA_15_LVL","mcce#Full_Iron_Armor_and_15_Levels");
    public static RunCategory ALL_WORKSTATIONS = new RunCategory("ALL_WORKSTATIONS","mcce#All_Workstations");
    public static RunCategory FULL_INV = new RunCategory("FULL_INV","mcce#Full_Inventory");
    public static RunCategory STACK_OF_LIME_WOOL = new RunCategory("STACK_OF_LIME_WOOL","mcce#Stack_of_Lime_Wool");
    public static RunCategory ALL_PORTALS = new RunCategory("ALL_PORTALS","mcce#All_Portals");
    public static RunCategory MINE_A_CHUNK = new RunCategory("MINE_A_CHUNK","mcce#Mine_a_Chunk");

    public static void checkAllBossesCompleted() {
        InGameTimer timer = InGameTimer.getInstance();
        if (timer.getCategory() == KILL_ALL_BOSSES) {
            if (timer.getMoreData(0) + timer.getMoreData(1) + timer.getMoreData(2) == 3) {
                InGameTimer.complete();
            }
        }
    }
}
