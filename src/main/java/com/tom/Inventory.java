package com.tom;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> inventar;
    private Shop shop;

    public Inventory() {
        inventar = new ArrayList<>();
        shop = new Shop();
    }

    public int getInventoryLength() {
        return(this.inventar.size());
    }

    public ArrayList<String> getArrayList() {
        return this.inventar;
    }

    public void addItem(String item) {
        inventar.add(item);
    }

    public void getItem(int index) {
        inventar.get(index);
    }

    public void getAllItems() {

    }

    public String displayItems() {
        return("");
    }
}