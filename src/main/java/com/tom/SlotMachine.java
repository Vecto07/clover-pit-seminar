package com.tom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SlotMachine {
    private HashMap<Double, String> emojiProbabilities;
    private HashMap<Double, String> emojiProbabilitiesInitial;
    private HashMap<String, Double> emojiValues;
    private HashMap<String, Double> emojiValuesInitial;
    private HashMap<String, Integer> emojiAppearances;
    private HashMap<String, Integer> emojiAppearancesInitial;
    private Inventory playerInventory;
    private double coins;
    private double coinsEarned;
    private double mult1;
    private double mult2;
    private double mult3;

    public SlotMachine() {
        this.coins = 0;
        this.coinsEarned = 0;
        this.emojiProbabilities = new HashMap<>();
        this.emojiProbabilitiesInitial = new HashMap<>();
        this.emojiValues = new HashMap<>();
        this.emojiValuesInitial = new HashMap<>();
        this.emojiAppearances = new HashMap<>();
        this.emojiAppearancesInitial = new HashMap<>();
        this.playerInventory = new Inventory();
        this.mult1 = 1.25;
        this.mult2 = 2;
        this.mult3 = 3.5;
        this.emojiProbabilities.put(0.44, "💩");
        this.emojiProbabilities.put(0.74, "❤️");
        this.emojiProbabilities.put(0.89, "👽");
        this.emojiProbabilities.put(0.94, "💯");
        this.emojiProbabilities.put(1.00, "☠️");
        this.emojiProbabilitiesInitial.put(0.44, "💩");
        this.emojiProbabilitiesInitial.put(0.74, "❤️");
        this.emojiProbabilitiesInitial.put(0.89, "👽");
        this.emojiProbabilitiesInitial.put(0.94, "💯");
        this.emojiProbabilitiesInitial.put(1.00, "☠️");
        this.emojiValues.put("💩", 15.0);
        this.emojiValues.put("❤️", 25.0);
        this.emojiValues.put("👽", 45.0);
        this.emojiValues.put("💯", 100.0);
        this.emojiValues.put("☠️", 5.0);
        this.emojiValuesInitial.put("💩", 15.0);
        this.emojiValuesInitial.put("❤️", 25.0);
        this.emojiValuesInitial.put("👽", 45.0);
        this.emojiValuesInitial.put("💯", 100.0);
        this.emojiValuesInitial.put("☠️", 5.0);
        this.emojiAppearances.put("💩", 0);
        this.emojiAppearances.put("❤️", 0);
        this.emojiAppearances.put("👽", 0);
        this.emojiAppearances.put("💯", 0);
        this.emojiAppearances.put("☠️", 0);
        this.emojiAppearancesInitial.put("💩", 0);
        this.emojiAppearancesInitial.put("❤️", 0);
        this.emojiAppearancesInitial.put("👽", 0);
        this.emojiAppearancesInitial.put("💯", 0);
        this.emojiAppearancesInitial.put("☠️", 0);
    }

    public void setCoinsEarned(double value) {
        this.coinsEarned = value;
    }

    public String spin() {
        double spinResult = Math.random();
        ArrayList<Double> hashMapKeys = new ArrayList<>(emojiProbabilities.keySet());
        Collections.sort(hashMapKeys);
        for(double mapContent: hashMapKeys) {
            if(spinResult > mapContent) {
                continue;
            }
            else if(spinResult <= mapContent) {
                return(emojiProbabilities.get(mapContent));
            }
        }
        return("null");
    }

    public void checkInventory() {
        HashMap<String, String> inventory = playerInventory.getArrayList();
        for(String content: inventory.keySet()) {
            if(content.equals("ShEC")) {
                this.emojiValues.put("💩", 25.0);
            }
            if(content.equals("HeEC")) {
                this.emojiValues.put("❤️", 35.0);
            }
            if(content.equals("AlEC")) {
                this.emojiValues.put("👽", 55.0);
            }
            if(content.equals("HuER")) {
                this.emojiValues.put("💯", 125.0);
            }
            if(content.equals("SkEL")) {
                this.emojiValues.put("☠️", 155.0);
                this.emojiProbabilities.clear();
                this.emojiProbabilities.put(0.25, "💩");
                this.emojiProbabilities.put(0.43, "❤️");
                this.emojiProbabilities.put(0.52, "👽");
                this.emojiProbabilities.put(0.54, "💯");
                this.emojiProbabilities.put(1.00, "☠️");
            }
            if(content.equals("ShMR")) {
                this.mult1 = this.mult1 * 1.2;
                this.mult2 = this.mult2 * 1.2;
                this.mult3 = this.mult3 * 1.2;
            }
        }
    }

    public String spinSeveralTimes(int amount) {
        this.coins = 0;
        ArrayList<String> emojis = new ArrayList<>();
        String output = "";
        this.checkInventory();
        this.emojiAppearances = new HashMap<>(this.emojiAppearancesInitial);
        String tempString = "";
        for(int i = 1; i <= amount; i++) {
            tempString = this.spin();
            int appearances = this.emojiAppearances.get(tempString);
            this.emojiAppearances.put(tempString, appearances + 1);
            emojis.add("[" + tempString + "]");
            coins = coins + this.calculateCoins(tempString);
            if(this.emojiAppearances.get("☠️") >= 4) {
                coinsEarned = 0;
                break;
            }
        }

        int initAppearances = 0;
        for(int appearances: this.emojiAppearances.values()) {
            if(appearances > initAppearances) {
                initAppearances = appearances;
            }
        }
        if(initAppearances == 2) {
            coins = coins * mult1;
        } else if(initAppearances == 3) {
            coins = coins * mult2;
        } else if(initAppearances == 4) {
            coins = coins * mult3;
        }
        coinsEarned = coinsEarned + coins;

        for(String content: emojis) {
            output = output + (content + " ");
        }
        this.emojiProbabilities = new HashMap<>(this.emojiProbabilitiesInitial);
        this.emojiValues = new HashMap<>(this.emojiValuesInitial);
        return output.trim();
    }

    public double getCoinsEarned() {
        return this.coinsEarned;
    }

    public double getCoins() {
        return this.coins;
    }

    public double calculateCoins(String emoji) {
        switch(emoji) {
            case("💩"): return(emojiValues.get("💩"));
            case("❤️"): return(emojiValues.get("❤️"));
            case("👽"): return(emojiValues.get("👽"));
            case("💯"): return(emojiValues.get("💯"));
            case("☠️"): return(emojiValues.get("☠️"));
        }
        return(0);
    }
}
