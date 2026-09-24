package com.tom;

import static dev.tamboui.toolkit.Toolkit.*;
import dev.tamboui.toolkit.app.ToolkitApp;
import dev.tamboui.toolkit.element.Element;


public class HelloDsl extends ToolkitApp {

    @Override
    protected Element render() {
        return panel("Hello",
            text("Welcome to TamboUI DSL!").bold().cyan(),
            spacer(),
            text("Press 'q' to quit").dim()
        ).rounded();
    }

    public static void main(String[] args) throws Exception {
        // App starten
        new HelloDsl().run();
    }
}