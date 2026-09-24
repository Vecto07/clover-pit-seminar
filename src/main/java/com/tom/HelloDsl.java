package com.tom;

import static dev.tamboui.toolkit.Toolkit.*;

import dev.tamboui.layout.Flex;
import dev.tamboui.style.Color;
import dev.tamboui.toolkit.app.ToolkitApp;
import dev.tamboui.toolkit.element.Element;


public class HelloDsl extends ToolkitApp {

    @Override
    protected Element render() {
        String text = "";
        return row(
                panel("Left").fill().borderColor(Color.BLACK),
                panel("Game",
                    text("Welcome to CLOVER PIT!").bold().cyan(),
                    spacer(),
                    text("[☠️][💯][☀️][💫]").bold().cyan().centered(),
                    spacer(),
                    text("Press Space to Spin").bold().cyan().centered(),
                    spacer(),
                    text("Press 'q' to quit").dim()
                        ).fill().rounded().flex(Flex.CENTER),
                panel("Right").fill().borderColor(Color.BLACK)

        );
    }

    public static void main(String[] args) throws Exception {
        // App starten

        new HelloDsl().run();
    }
}