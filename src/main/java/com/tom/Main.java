package com.tom;

import dev.tamboui.toolkit.app.ToolkitApp;
import dev.tamboui.toolkit.element.Element;

public class Main extends ToolkitApp {

    public enum Screen {
        WELCOME,
        GAME
    }

    private Screen currentScreen = Screen.WELCOME;
    private final Welcome welcomeScreen;
    private final UI gameScreen;

    public Main() {
        this.welcomeScreen = new Welcome(this);
        this.gameScreen = new UI(this);
    }

    @Override
    protected void onStart() {
        welcomeScreen.onStart(runner());
    }

    @Override
    protected void onStop() {
        welcomeScreen.onStop();
    }

    public void startGame() {
        welcomeScreen.onStop(); // Timer stoppen
        this.currentScreen = Screen.GAME;
    }

    public void runOnRenderThread(Runnable action) {
        runner().runOnRenderThread(action);
    }

    @Override
    protected Element render() {
        return switch (currentScreen) {
            case WELCOME -> welcomeScreen.render();
            case GAME -> gameScreen.render();
        };
    }

    public static void main(String[] args) throws Exception {
        new Main().run();
    }
}