package game;

import javafx.scene.shape.Rectangle;

public class TriggerDetection implements IObserver {

    GameEngine gameEngine;

    public TriggerDetection(GameEngine subject) {
        this.gameEngine = subject;
        this.gameEngine.registerObserver(this);
    }

    @Override
    public void update() {
        double minX = gameEngine.getTriggerField().boundsInParentProperty().getValue().getMinX();
        double minY = gameEngine.getTriggerField().boundsInParentProperty().getValue().getMinY();
        double width = gameEngine.getTriggerField().boundsInParentProperty().getValue().getWidth();
        double height = gameEngine.getTriggerField().boundsInParentProperty().getValue().getHeight();
        Rectangle actionRadius = new Rectangle(minX-1, minY-1, width+2, height+2);

        gameEngine.setTrigger(actionRadius.getBoundsInParent().intersects(gameEngine.getActionSquare().boundsInParentProperty().getValue()));
    }
}