package com.tom;

import static dev.tamboui.toolkit.Toolkit.*;

import dev.tamboui.layout.Flex;
import dev.tamboui.style.Color;
import dev.tamboui.toolkit.app.ToolkitApp;
import dev.tamboui.toolkit.element.Element;
import dev.tamboui.toolkit.event.EventResult;

import java.util.concurrent.ThreadLocalRandom;


public class UI extends ToolkitApp {
    private final Main app;
    public UI(Main app) {
        this.app = app;
    }
    SlotMachine slotMachine = new SlotMachine();
    Inventory inventory = new Inventory();
    private int schulden = 1500;
    private String slots = "[ ][ ][ ][ ]";
    private String statusText = "Press Space to Spin";
    private boolean spinning;
    private Thread slotAnimation;

    private static final String[] SLOT_SYMBOLS = {"💩", "❤️", "👽", "💯", "☠️"};

    @Override
    protected Element render() {
        String text = "";
        return row(
                panel("Left").fill().borderColor(Color.BLACK),
                panel("Game",
                    panel(
                        text("Welcome to CLOVER PIT!").bold().cyan().centered(),
                        spacer(),
                        text(slots).bold().cyan().centered(),
                        spacer()

                    )
                        .percent(33)
                        .rounded()
                        .flex(Flex.CENTER),
                    panel(
                        text(statusText).bold().cyan().centered(),
                        spacer(),
                        row(
                                text("Schulden: " + (schulden - slotMachine.getCoinsEarned()) + "$").bold().cyan(),
                                spacer(),
                                text("Coins gesamt: " + slotMachine.getCoinsEarned()).bold().cyan(),
                                spacer(),
                                text("Tag: 1").bold().cyan()
                        ),
                        text("Press 'q' to quit").dim().centered()
                    )
                        .fill()
                        .rounded()
                        .flex(Flex.CENTER)
                ).fill().rounded().vertical().onKeyEvent(event -> {
                    if (event.isChar(' ') && !spinning) {
                        startSpinAnimation();
                        return EventResult.HANDLED;
                    }

                    return EventResult.UNHANDLED;
                }),
                panel("Inventar",
                        row(
                            panel("Item 1",
                                text(inventory.getItem(0))
                            ).rounded(),
                            panel("Item 2",
                                    text(inventory.getItem(1))
                            ).rounded(),
                            panel("Item 3",
                                    text(inventory.getItem(2))
                            ).rounded(),
                            panel("Item 4",
                                    text(inventory.getItem(3))
                            ).rounded(),
                            panel("Item 5",
                                    text(inventory.getItem(4))
                            ).rounded()
                        ).flex(Flex.SPACE_AROUND)
                ).fill().rounded()

        );
    }

    public void spinSlots() {
        slots = slotMachine.spinSeveralTimes(4);
    }

    private void startSpinAnimation() {
        spinning = true;
        statusText = "Es dreht sich...";
        slotAnimation = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    app.runOnRenderThread(() -> slots = randomSlots());
                    Thread.sleep(35L + i * 12L);
                }
                app.runOnRenderThread(() -> {
                    spinSlots();
                    statusText = "Coins: " + slotMachine.getCoins();
                    spinning = false;
                });
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        });
        slotAnimation.start();
    }

    private String randomSlots() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String symbol = SLOT_SYMBOLS[
                    ThreadLocalRandom.current().nextInt(SLOT_SYMBOLS.length)
            ];
            result.append('[').append(symbol).append("] ");
        }
        return result.toString().trim();
    }

    @Override
    protected void onStop() {
        if (slotAnimation != null) {
            slotAnimation.interrupt();
        }
    }

    public static void main(String[] args) throws Exception {
        // App starten
        new Main().run();
    }
}