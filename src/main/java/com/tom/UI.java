package com.tom;

import static dev.tamboui.toolkit.Toolkit.*;

import dev.tamboui.layout.Flex;
import dev.tamboui.style.Color;
import dev.tamboui.toolkit.app.ToolkitApp;
import dev.tamboui.toolkit.element.Element;
import dev.tamboui.toolkit.event.EventResult;


public class UI extends ToolkitApp {
    SlotMachine slotMachine = new SlotMachine();
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
                        slots = slotMachine.spinSeveralTimes(4);
                        statusText = "SPACE was pressed";
                        return EventResult.HANDLED;
                    }

                    return EventResult.UNHANDLED;
                }),
                panel("Right").fill().borderColor(Color.BLACK)

        );
    }

    public static void main(String[] args) throws Exception {
        // App starten

        new UI().run();
    }
}