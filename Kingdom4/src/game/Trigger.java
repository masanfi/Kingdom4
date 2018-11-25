package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Trigger {

    private GameEngine gameEngine;
    private Shape trigger;

    public Trigger(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        this.trigger = new Rectangle(64, 64);
        this.trigger.setFill(Color.PURPLE);
        this.trigger.setLayoutX(960);
        this.trigger.setLayoutY(576);

        gameEngine.setTriggerField(this.trigger);
    }
}
