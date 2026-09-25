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


public class Welcome extends ToolkitApp {
    private static final String[] Willkommen = {
            "Willkommen bei Clover Pit!",
            "Warte... Du hast $600 Schulden??",
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
            "GEH JETZT ENDLICH, q DRÜCKEN!"
            "TSCHÜSS",
            "TSCHÜSS",
            "TSCHÜSS",
            "TSCHÜSS",
            "TSCHÜSS"

    };

    private int messageIndex;
    private int visibleCharacters;
    private ToolkitRunner.ScheduledAction typingAnimation;

    @Override
    protected void onStart() {
        ToolkitRunner appRunner = runner();
        typingAnimation = appRunner.scheduleRepeating(
                () -> appRunner.runOnRenderThread(this::typeNextCharacter),
                Duration.ofMillis(55)
        );
    }

    @Override
    protected void onStop() {
        if (typingAnimation != null) {
            typingAnimation.cancel();
        }
    }

    private void typeNextCharacter() {
        String currentMessage = Willkommen[messageIndex];
        if (visibleCharacters < currentMessage.length()) {
            visibleCharacters++;
        }
    }

    private void showNextMessage() {
        messageIndex = (messageIndex + 1) % Willkommen.length;
        visibleCharacters = 0;
    }

    private String visibleMessage() {
        String currentMessage = Willkommen[messageIndex];
        return currentMessage.substring(0, visibleCharacters);
    }

    @Override
    protected Element render() {
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
                    if (event.isChar(' ') || event.isConfirm()) {
                        showNextMessage();
                        return EventResult.HANDLED;
                    }

                    return EventResult.UNHANDLED;
                }),
                panel("Rechts").fill().borderColor(Color.BLACK)

        );
    }

    public static void main(String[] args) throws Exception {
        new Welcome().run();
    }
}