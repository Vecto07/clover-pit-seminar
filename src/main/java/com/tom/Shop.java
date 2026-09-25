package com.tom;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private HashMap<String, String> items;

    public Shop() {
        this.items = new HashMap<>();
        items.put("ShEC", "Erhöht den Wert aller Kackhaufen um 10.");
        items.put("HeEC", "Erhöht den Wert aller Herzen um 10.");
        items.put("AlEC", "Erhöht den Wert aller Aliens um 10.");
        items.put("HuER", "Erhöht den Wert aller 100 um 25.");
        items.put("SkEL", "Erhöht den Wert aller Totenköpfe um 150 und setzt die Wahrscheinlichkeit auf 35%.");
    }


}
