package game;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

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

        this.fillWisemanWithWisdom();
    }

    private void fillWisemanWithWisdom() {
        wisemanText.add("Aluminiumfolie reißt nicht\nso leicht, wenn man sie\nvor Gebrauch vollflächig auf\nRigipsplatten klebt.");
        wisemanText.add("Mein Sohn!\nDie Schule des Lebens\nhat niemals Ferien.");
        wisemanText.add("Schmutziges Geschirr\nschimmelt nicht, wenn\nman es in der Gefriertruhe\naufbewahrt.");
        wisemanText.add("Alte Matrosen-Weisheit:\nLieber Rum trinken,\nals rumsitzen!");
        wisemanText.add("Zwiebeln statt Kiwis\nkaufen! Zwiebeln sind\nbilliger und länger\nhaltbar.");
    }

    public void startConversation(Trigger trigger) {
        Timeline timeline = new Timeline();
        double triggerX = trigger.getCoordinates().getX();
        double triggerY = trigger.getCoordinates().getY();
        double playerX = gameEngine.getPlayer()[0].getX() + gameEngine.getTileSize();
        double playerY = gameEngine.getPlayer()[0].getY() + gameEngine.getTileSize();

        if (trigger.getName().contentEquals("lady")) {
            if(!this.getStatusSpeechBubble()) {
                if (character.get("lady") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "Willkommen im\nKönigreich Faboma,\nOreh!", 2, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000), ae -> this.showSpeechBubble(triggerX, triggerY, "Hier gibt es\neiniges zu\nentdecken!", 2, Color.RED, Color.WHITE)));
                    timeline.play();
                    character.put("lady", 1);
                }
                else if (character.get("lady") == 1) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "Hinfort mit dir!", 2, Color.RED, Color.WHITE)));
                    character.put("lady", 2);
                    timeline.play();
                }
                else {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "Du bist\nvielleicht ein bisschen\nanstrengend!", 2, Color.RED, Color.WHITE)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("wiseman")) {
            if(!this.getStatusSpeechBubble()) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, wisemanText.size());
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, wisemanText.get(randomNum), 2, Color.DARKGRAY, Color.BLACK)));
                timeline.play();
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("blacksmith")) {
            if(!this.getStatusSpeechBubble()) {
                if (character.get("blacksmith") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "Harrr! Hast du\nheute schon dein\nKinderbier getrunken?", 2, Color.BLACK, Color.WHITE)));
                    timeline.play();
                    character.put("blacksmith", 1);
                }
                else if (character.get("blacksmith") == 1){
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich bin hier\nnur der Schmied.", 2, Color.BLACK, Color.WHITE)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("fisherman")) {
            if(!this.getStatusSpeechBubble()) {
                if (character.get("fisherman") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "Wenn du so\nprogrammierst, wie\ndu aussiehst, ist die\nWelt verloren!", 2, Color.YELLOW, Color.BLACK)));
                    timeline.play();
                    character.put("fisherman", 1);
                }
                else if (character.get("fisherman") == 1) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "Wusstest du, dass\ndass mein Beruf im\nEnglischen \"fisher man\"\nund nicht \"fishman\"\ngenannt wird?", 2, Color.YELLOW, Color.BLACK)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("knight")) {
            if(!this.getStatusSpeechBubble()) {
                if (character.get("knight") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "Ohne Fisch kommst du\nhier nicht durch", 2, Color.NAVY, Color.WHITE)));
                    timeline.play();
                    character.put("knight", 1);
                }
                else if (character.get("knight") == 1) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Moin!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), ae -> this.showSpeechBubble(triggerX, triggerY, "How much is\nthe fish?", 2, Color.NAVY, Color.WHITE)));
                    timeline.play();
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
                Duration.seconds(2),
                ae -> {
                    gameEngine.setSpeed(0);
                    gameEngine.getTextOver().setVisible(false);
                    gameEngine.setSpeed(200);
                    gameEngine.getTextOver().getChildren().removeAll(littlePointer, bubble, text);
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
