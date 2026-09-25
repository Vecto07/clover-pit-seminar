package com.tom;

import static dev.tamboui.toolkit.Toolkit.*;

import dev.tamboui.layout.Flex;
import dev.tamboui.style.Color;
import dev.tamboui.toolkit.app.ToolkitApp;
import dev.tamboui.toolkit.element.Element;
import dev.tamboui.toolkit.event.EventResult;
import dev.tamboui.widgets.table.Row;

import java.time.Duration;


public class UI extends ToolkitApp {
    private final Main app;
    public UI(Main app) {
        this.app = app;
    }
    SlotMachine slotMachine = new SlotMachine();
    Inventory inventory = new Inventory();
    private String slots = "[ ][ ][ ][ ]";
    private String statusText = "Press Space to Spin";
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
                        text("Press 'q' to quit").dim().centered()
                    )
                        .fill()
                        .rounded()
                        .flex(Flex.CENTER)
                ).fill().rounded().vertical().onKeyEvent(event -> {
                    if (event.isChar(' ')) {
                        spinSlots();
                        String getCoins = ("Coins: " + slotMachine.getCoins());
                        statusText = getCoins;
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

    public static void main(String[] args) throws Exception {
        // App starten
        new Main().run();
    }
}