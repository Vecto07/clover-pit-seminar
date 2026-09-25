package com.tom;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private HashMap<String, String> inventar;
    private Shop shop;

    public Inventory() {
        inventar = new HashMap<>();
        shop = new Shop();
        this.inventar.put("ShEC", "💩");
    }

    public int getInventoryLength() {
        return(this.inventar.size());
    }

    public HashMap<String, String> getArrayList() {
        return this.inventar;
    }

    public void addItem(String item, String icon) {
        inventar.put(item, icon);
    }

    public String getItem(int index) {
        if(inventar.size() - 1 < index) {
            return("X");
        }
        return(inventar.get(inventar.keySet().toArray()[index]));
    }
}