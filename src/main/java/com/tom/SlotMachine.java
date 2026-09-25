package com.tom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SlotMachine {
    private HashMap<Double, String> emojiProbabilities;
    private HashMap<String, Double> emojiValues;
    private double coins;

    public SlotMachine() {
        this.coins = 0;
        this.emojiProbabilities = new HashMap<>();
        this.emojiValues = new HashMap<>();
        this.emojiProbabilities.put(0.44, "💩");
        this.emojiProbabilities.put(0.74, "❤️");
        this.emojiProbabilities.put(0.89, "👽");
        this.emojiProbabilities.put(0.94, "💯");
        this.emojiProbabilities.put(1.00, "☠️");
        this.emojiValues.put("💩", 15.0);
        this.emojiValues.put("❤️", 25.0);
        this.emojiValues.put("👽", 45.0);
        this.emojiValues.put("💯", 100.0);
        this.emojiValues.put("☠️", 5.0);
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

    public String spinSeveralTimes(int amount) {
        ArrayList<String> emojis = new ArrayList<>();
        ArrayList<String> emojisClean = new ArrayList<>();
        String output = "";
        for(int i = 1; i <= amount; i++) {
            String tempString = this.spin();
            emojis.add("[" + tempString + "]");
            emojisClean.add(tempString);
            coins = coins + this.calculateCoins(tempString);
        }
        for(String content: emojis) {
            output = output + (content + " ");
        }
        return output.trim();
    }

    public double getCoins() {
        double tempCoins = this.coins;
        this.coins = 0;
        return tempCoins;
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
