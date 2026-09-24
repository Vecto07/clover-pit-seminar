import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SlotMachine {
    private HashMap<Double, String> emojiProbabilities;
    private int coins;

    public SlotMachine() {
        this.coins = 0;
        this.emojiProbabilities = new HashMap<>();
        this.emojiProbabilities.put(0.44, "💩");
        this.emojiProbabilities.put(0.74, "❤️");
        this.emojiProbabilities.put(0.89, "👽");
        this.emojiProbabilities.put(0.94, "💯");
        this.emojiProbabilities.put(1.00, "☠️");
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
        String output = "";
        for(int i = 1; i <= amount; i++) {
            String tempString = this.spin();
            emojis.add("[" + tempString + "]");
            coins = coins + this.calculateCoins(tempString);
        }
        for(String content: emojis) {
            output = output + (content + " ");
        }
        return output.trim();
    }

    public int getCoins() {
        int tempCoins = this.coins;
        this.coins = 0;
        return tempCoins;
    }

    public int calculateCoins(String emoji) {
        switch(emoji) {
            case("💩"): return(15);
            case("❤️"): return(25);
            case("👽"): return(45);
            case("💯"): return(100);
            case("☠️"): return(5);
        }
        return(0);
    }
}
