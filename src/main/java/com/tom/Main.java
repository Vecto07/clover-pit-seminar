package com.tom;
public class Main {
    public static void main(String[] args) throws Exception {
        new UI().run();
        SlotMachine slotMachine = new SlotMachine();
        System.out.println(slotMachine.spinSeveralTimes(4));
        System.out.println(slotMachine.getCoins());
    }
}
