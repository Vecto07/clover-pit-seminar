package com.tom;

import static dev.tamboui.toolkit.Toolkit.*;

import dev.tamboui.layout.Flex;
import dev.tamboui.style.Color;
import dev.tamboui.style.Overflow;
import dev.tamboui.toolkit.app.ToolkitApp;
import dev.tamboui.toolkit.app.ToolkitRunner;
import dev.tamboui.toolkit.element.Element;
import dev.tamboui.toolkit.event.EventResult;

import java.time.Duration;

public class Welcome {

    private static final String[] WILLKOMMEN = {
            "Willkommen bei Clover Shit!",
            "Warte... Du hast $1500 Schulden??",
            "Du hast Glück im Unglück! Du musst sie zwar innerhalb von 5 Tagen zurückzahlen...",
            "Aber indem du jeden Tag am Glücksrad drehst, wirst du es sicher bald zusammen haben ;)",
            "So läuft es:",
            "Jeden Morgen bekommst du etwas Geld. Das kannst du nutzen, um Spins zu kaufen.",
            "Die Spins kannst du mit Shop-Items \"aufbessern\". Den Shop kannst du nach deinen Spins besuchen.",
            "Am Ende des Tages kannst du dich entscheiden, ob du schon Schulden zurückzahlen möchtest. Dein gespartes Geld wird verzinst - aber die Schulden auch!",
            "Halte dich fern von den Totenköpfen, nutze deine Items und zahle deine Schulden zurück!",
            "Viel Erfolg <3",
            "Drücke q für Start!",
            "DRÜCKE q UM ZU STARTEN!",
            "Was machst du hier??????",
            "GEH BITTE JETZT; EINFACH q DRÜCKEN!",
            "AAAAAAAAAAAHHHHHHHHHH",
            "Ich sags noch einmal, q DRÜCKEN!",
            "Willst du nicht spielen?",
            "GEH JETZT ENDLICH, q DRÜCKEN!",
            "TSCHÜSS",
            "TSCHÜSS",
            "TSCHÜSS",
            "TSCHÜSS",
            "TSCHÜSS"

    };

    private final Main app;
    private int messageIndex = 0;
    private int visibleCharacters = 0;
    private ToolkitRunner.ScheduledAction typingAnimation;

    public Welcome(Main app) {
        this.app = app;
    }

    public void onStart(ToolkitRunner appRunner) {
        typingAnimation = appRunner.scheduleRepeating(
                () -> appRunner.runOnRenderThread(this::typeNextCharacter),
                Duration.ofMillis(55)
        );
    }

    public void onStop() {
        if (typingAnimation != null) {
            typingAnimation.cancel();
        }
    }

    private void typeNextCharacter() {
        String currentMessage = WILLKOMMEN[messageIndex];
        if (visibleCharacters < currentMessage.length()) {
            visibleCharacters++;
        }
    }

    private void showNextMessage() {
        messageIndex++;
        if (messageIndex >= WILLKOMMEN.length) {
            // Nach dem letzten Text automatisch zum Spiel wechseln
            app.startGame();
            return;
        }
        visibleCharacters = 0;
    }

    private String visibleMessage() {
        // Guard against index out of bounds during scene transition
        int safeIndex = Math.min(messageIndex, WILLKOMMEN.length - 1);
        String currentMessage = WILLKOMMEN[safeIndex];
        return currentMessage.substring(0, Math.min(visibleCharacters, currentMessage.length()));
    }

    public Element render() {
        return row(
                panel("Links").fill().borderColor(Color.BLACK),
                panel("Spiel",
                        panel(
                                spacer(),
                                text("[❤️][❤️][❤️][❤️]").bold().cyan().centered(),
                                spacer()
                        )
                                .percent(33)
                                .rounded()
                                .flex(Flex.CENTER),
                        panel(
                                text(visibleMessage()).bold().cyan().centered().overflow(Overflow.WRAP_WORD),
                                spacer(),
                                text("Leertaste für den nächsten Text").dim().centered(),
                                spacer(),
                                spacer(),
                                text("Drücke 'q' zum Starten").dim().centered()
                        )
                                .fill()
                                .rounded()
                                .flex(Flex.CENTER)
                ).fill().rounded().vertical().onKeyEvent(event -> {
                    // Starten bei 'q' oder 'Q'
                    if (event.isChar('q') || event.isChar('Q')) {
                        app.startGame();
                        return EventResult.HANDLED;
                    }

                    // Nächster Text bei Leertaste oder Enter
                    if (event.isChar(' ') || event.isConfirm()) {
                        showNextMessage();
                        return EventResult.HANDLED;
                    }

                    return EventResult.UNHANDLED;
                }),
                panel("Rechts").fill().borderColor(Color.BLACK)

        );
    }
}