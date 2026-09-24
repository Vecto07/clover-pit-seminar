import java.util.ArrayList;
import java.util.HashMap;

public class SlotMachine {
    private HashMap<Double, String> emojiProbabilities;

    public SlotMachine() {
        this.emojiProbabilities = new HashMap<>();
        this.emojiProbabilities.put(0.44, "💩");
        this.emojiProbabilities.put(0.74, "❤️");
        this.emojiProbabilities.put(0.89, "👽");
        this.emojiProbabilities.put(0.94, "💯");
        this.emojiProbabilities.put(0.99, "☠️");
    }

    public String spin() {
        double spinResult = Math.random();
        for(double mapContent: emojiProbabilities.keySet()) {
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
            emojis.add("[" + this.spin() + "]");
        }
        for(String content: emojis) {
            output = output + (content + " ");
        }
        return output;
    }
}
