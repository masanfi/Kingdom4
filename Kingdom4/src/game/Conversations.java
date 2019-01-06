package game;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * This enables conversations in the Kingdom of Faboma
 * Copyright (c) 2018-2019 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class Conversations {

    private GameEngine gameEngine;
    private List<String> wisemanText = new ArrayList<String>();
    private boolean statusSpeechBubble = false;
    private Map<String, Integer> character = new HashMap<>();

    public Conversations(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        character.put("lady", 0);
        character.put("fisherman", 0);
        character.put("blacksmith", 0);
        character.put("knight", 0);
        character.put("wiseman", 0);

        this.fillWisemanWithWisdom();
    }

    private void fillWisemanWithWisdom() {
        wisemanText.add("Mein Sohn!\nDie Schule des Lebens\nhat niemals Ferien.");
        wisemanText.add("Im Haus des Glücks\nist der Warteraum das\ngrößte Zimmer.");
        wisemanText.add("Schmutziges Geschirr\nschimmelt nicht, wenn\nman es in der Gefrier-\ntruhe aufbewahrt.");
        wisemanText.add("Alte Matrosen-Weisheit:\nLieber Rum trinken,\nals rumsitzen!");
        wisemanText.add("Zwiebeln statt Kiwis\nkaufen! Zwiebeln sind\nbilliger und länger\nhaltbar.");
    }

    public void startConversation(Trigger trigger) {
        Timeline timeline = new Timeline();
        double triggerX = trigger.getCoordinates().getX();
        double triggerY = trigger.getCoordinates().getY();
        double playerX = gameEngine.getPlayer()[0].getX() + gameEngine.getTileSize();
        double playerY = gameEngine.getPlayer()[0].getY() + gameEngine.getTileSize();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> gameEngine.setMovement(false)));
        timeline.setOnFinished(t->{
    		gameEngine.setMovement(true);
    	});
        if (trigger.getName().contentEquals("lady")) {
            if(!this.getStatusSpeechBubble()) {
                if (character.get("lady") == 0) {
                	
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Willkommen zurück im\nKönigreich Faboma,\nOreh!", 2, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4200), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich weiß, wer du\nbist, du mächtiger Held.\nWo warst du?", 2, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6600), ae -> this.showSpeechBubble(playerX, playerY, "Ich war auf\nGeschäftsreise!\nWas ist denn passiert?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(8800), ae -> this.showSpeechBubble(triggerX, triggerY, "Unsere Prinzessin Adlez\nwurde aus der Burg\nentführt!\nDu musst sie finden!", 3, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(11900), ae -> this.showSpeechBubble(playerX, playerY, "Schreck lass nach!\nWo könnte sie denn sein?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(14000), ae -> this.showSpeechBubble(triggerX, triggerY, "Man munkelt, dass du sie\nauf der anderen Seite\nder Brücke finden kannst.", 2, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(16100), ae -> this.showSpeechBubble(playerX, playerY, "Das sehe ich mir sofort\nan!", 2, Color.WHITE, Color.BLACK)));
              	
                    timeline.play();
                    character.put("lady", 1);
                }
                else if (character.get("lady") == 1) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Wie heißt du eigentlich?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Oreh, wir haben im\nNachbarkönigreich\nzusammen Maschinen-\nbau studiert.", 2, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4200), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich bin Ydal!\nHast du die Prinzessin\nschon finden können?", 2, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6300), ae -> this.showSpeechBubble(playerX, playerY, "Ich bin kurz vor dem\ngroßen Durchbruch!\nMuss los, bye!", 2, Color.WHITE, Color.BLACK)));
                    timeline.play();
                    character.put("lady", 2);
                }
                else {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Hallo Ydal!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Du bist\nvielleicht eine große\nNervensäge!\nSuch die Prinzessin!", 2, Color.RED, Color.WHITE)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("wiseman")) {
            if(!this.getStatusSpeechBubble()) {
                if (character.get("wiseman") == 0) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, wisemanText.size());
                    if (character.get("lady") == 1) {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag, weiser Man!\nIch suche die Prinzessin.\nWeißt du, wo sie sein\nkönnte?", 2, Color.WHITE, Color.BLACK)));
                    }
                    else {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag, weiser Man!\nWas gibt's Neues?", 2, Color.WHITE, Color.BLACK)));
                    }
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, wisemanText.get(randomNum), 3, Color.DARKGRAY, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5200), ae -> this.showSpeechBubble(playerX, playerY, "Das schreibe ich mir\nsofort auf.", 2, Color.WHITE, Color.BLACK)));
                    timeline.play();
                    character.put("wiseman", 1);
                }
                else if (character.get("wiseman") == 1) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, wisemanText.size());
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Eine Frage...", 1, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1100), ae -> this.showSpeechBubble(triggerX, triggerY, wisemanText.get(randomNum), 3, Color.DARKGRAY, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4200), ae -> this.showSpeechBubble(playerX, playerY, "Interessant.", 2, Color.WHITE, Color.BLACK)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("blacksmith")) {
            if(!this.getStatusSpeechBubble()) {
                if (character.get("blacksmith") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Jeder kennt dich, Oreh.\nHast du das Geld\ndabei, das du mir noch\nschuldest?", 3, Color.BLACK, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5200), ae -> this.showSpeechBubble(playerX, playerY, "Ja, sicher. Ich hab\ndas, äh, hier.\nGeb ich dir gleich.", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(7300), ae -> this.showSpeechBubble(playerX, playerY, "Wie heißt du denn,\nedler Mann?\nIch war so lange weg.", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(9400), ae -> this.showSpeechBubble(triggerX, triggerY, "Du warst zwei Tage weg.\nAuf \"Geschäftsreise\".\nIch bin Fabian Schmied!", 3, Color.BLACK, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(12500), ae -> this.showSpeechBubble(playerX, playerY, "Stimmt!\nLeider habe ich immer\nso derbe viel zu tun!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(14600), ae -> this.showSpeechBubble(triggerX, triggerY, "Ja, ja.\nBring mir einen Hasen,\nwenn du schon kein\nGeld hast.", 3, Color.BLACK, Color.WHITE)));
                    timeline.play();
                    character.put("blacksmith", 1);
                }
                else if (character.get("blacksmith") == 1){
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Hallo!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Hat der mächtige Held\neinen Hasen gefunden?", 2, Color.BLACK, Color.WHITE)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("fisherman")) {
            if(!this.getStatusSpeechBubble()) {
                if (character.get("fisherman") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag, edler Fischer!\nIch bin Oreh, ein...", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Wenn du jetzt sagst,\ndass du ein mächtiger\nHeld bist, gibt's was\nauf die Löffel!", 3, Color.YELLOW, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5200), ae -> this.showSpeechBubble(playerX, playerY, "Wollte ich nicht sagen!\nWie laufen die Geschäfte?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(7300), ae -> this.showSpeechBubble(triggerX, triggerY, "Im Angelbusiness bin ich\nein großer Fisch.\nWenn du weißt, wie\nich meine.", 3, Color.YELLOW, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10400), ae -> this.showSpeechBubble(triggerX, triggerY, "Aber, schau mal, die\nTruhe hier. Wo ist\ndenn der Schlüssel dafür?", 3, Color.YELLOW, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(13500), ae -> this.showSpeechBubble(playerX, playerY, "Also, ich hab den nicht\nim Wald verloren.", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(15600), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich habe nichts von\neinem Wald gesagt.\nBring mir den Schlüssel!", 3, Color.YELLOW, Color.BLACK)));
                    timeline.play();
                    character.put("fisherman", 1);
                }
                else if (character.get("fisherman") == 1) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Nochmal wegen...", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "Hast du den Schlüssel?", 2, Color.YELLOW, Color.BLACK)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("knight")) {
            if(!this.getStatusSpeechBubble()) {
                if (character.get("knight") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag, edler Ritter!\nIch...", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Was willst du?", 2, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4200), ae -> this.showSpeechBubble(playerX, playerY, "Ich bin ein...", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6300), ae -> this.showSpeechBubble(triggerX, triggerY, "...Typ mit einem Problem,\nwenn du nicht sofort\nverschwindest.", 2, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(8400), ae -> this.showSpeechBubble(playerX, playerY, "Sieh mal, ich muss nur\nauf die andere Seite.", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10500), ae -> this.showSpeechBubble(triggerX, triggerY, "Solange ich hier stehe,\nkommst du nicht durch.", 2, Color.NAVY, Color.WHITE)));
                    timeline.play();
                    character.put("knight", 1);
                }
                else if (character.get("knight") == 1) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Ich muss über die Brücke!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Vergiss es.", 2, Color.NAVY, Color.WHITE)));
                    timeline.play();
                    character.put("knight", 2);
                }
                else if (character.get("knight") == 2) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Kann ich ganz bitte,\nbitte mal kurz über\ndie Brücke?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(playerX, playerY, "Ich will nach ganz oben\nin den Highscore!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4200), ae -> this.showSpeechBubble(triggerX, triggerY, "Klar!", 2, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6300), ae -> this.showSpeechBubble(triggerX, triggerY, "Vergiss es.\nAußerdem habe ich\nHunger und bin\nschlecht gelaunt!", 2, Color.NAVY, Color.WHITE)));
                    timeline.play();
                    character.put("knight", 2);
                }
                this.setStatusSpeechBubble(true);
            }
        }
    }

    /**
     * Shows a speech bubble of a conversation.
     * @param x
     * @param y
     * @param textString
     * @param duration
     * @param backgroundColor
     * @param textColor
     */
    public void showSpeechBubble(double x, double y, String textString, int duration, Color backgroundColor, Color textColor) {
        gameEngine.setSpeed(0);
        Polygon littlePointer = new Polygon();
        littlePointer.getPoints().addAll(new Double[]{ x + 20, y - 20, x + 40, y, x + 64, y - 20 });
        littlePointer.setFill(backgroundColor);
        Rectangle bubble = new Rectangle(x, y - 2 * gameEngine.getTileSize() + 20, 200, 100);
        bubble.setFill(backgroundColor);

        Text text = new Text(x + 10, y - 80, textString);
        text.setFont(Font.font ("Verdana", 14));
        text.setFill(textColor);

        gameEngine.getTextOver().getChildren().addAll(littlePointer, bubble, text);
        gameEngine.getTextOver().setVisible(true);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(duration),
                ae -> {
                    gameEngine.getTextOver().setVisible(false);
                    gameEngine.getTextOver().getChildren().clear();
                    gameEngine.setSpeed(200);
                }));
        timeline.play();
    }

    public boolean getStatusSpeechBubble() {
        return statusSpeechBubble;
    }

    public void setStatusSpeechBubble(boolean statusSpeechBubble) {
        this.statusSpeechBubble = statusSpeechBubble;
    }
}
