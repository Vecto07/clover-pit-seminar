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
        ArrayList<String> inventory = playerInventory.getArrayList();
        for(String content: inventory) {
            if(content == "ShEC") {
                this.emojiValues.put("💩", 25.0);
            }
            if(content == "HeEC") {
                this.emojiValues.put("❤️", 35.0);
            }
            if(content == "AlEC") {
                this.emojiValues.put("👽", 55.0);
            }
            if(content == "HuER") {
                this.emojiValues.put("💯", 125.0);
            }
            if(content == "SkEL") {
                this.emojiValues.put("☠️", 155.0);
                this.emojiProbabilities.clear();
                this.emojiProbabilities.put(0.31, "💩");
                this.emojiProbabilities.put(0.52, "❤️");
                this.emojiProbabilities.put(0.62, "👽");
                this.emojiProbabilities.put(0.64, "💯");
                this.emojiProbabilities.put(1.00, "☠️");
            }
        }
    }

    public String spinSeveralTimes(int amount) {
        ArrayList<String> emojis = new ArrayList<>();
        ArrayList<String> emojisClean = new ArrayList<>();
        double mult1 = 1.25;
        double mult2 = 2;
        double mult3 = 1.6;
        String output = "";
        this.checkInventory();
        for(int i = 1; i <= amount; i++) {
            String tempString = this.spin();
            int appearances = this.emojiAppearances.get(tempString);
            this.emojiAppearances.put(tempString, appearances + 1);
            emojis.add("[" + tempString + "]");
            emojisClean.add(tempString);
            if(this.emojiAppearances.get(tempString) == 2) {
                coins = coins * mult1;
            } else if(this.emojiAppearances.get(tempString) == 3) {
                coins = coins * mult2;
            } else if(this.emojiAppearances.get(tempString) == 4) {
                coins = coins * mult3;
            }
            if(this.emojiAppearances.get("☠️") == 4) {
                coinsEarned = 0;
            }
            coinsEarned = coinsEarned + this.calculateCoins(tempString);
            this.emojiAppearances = this.emojiAppearancesInitial;
        }

        for(String content: emojis) {
            output = output + (content + " ");
        }
        this.emojiProbabilities = this.emojiProbabilitiesInitial;
        this.emojiValues = this.emojiValuesInitial;
        return output.trim();
    }

    public double getCoins() {
        return this.coinsEarned;
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
