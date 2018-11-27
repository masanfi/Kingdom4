package game;

import javafx.scene.shape.Rectangle;

public class TriggerDetection implements IObserver {

    GameEngine gameEngine;

    int triggers;

    public TriggerDetection(GameEngine subject) {
        this.gameEngine = subject;
        this.gameEngine.registerObserver(this);
    }

    @Override
    public void update() {
        triggers = 0;

        gameEngine.getTriggerObject().forEach(trigger->{
            double minX = trigger.getCoordinates().getX();
            double minY = trigger.getCoordinates().getY();
            double width = gameEngine.getTileSize();
            double height = gameEngine.getTileSize();

            Rectangle actionSquareFuture = gameEngine.getActionSquareFuture();
            Rectangle actionRadius = new Rectangle(minX+2, minY+2, width+4, height+4);

            if (actionRadius.getBoundsInParent().intersects(actionSquareFuture.boundsInParentProperty().getValue())) {
                triggers++;
            }
        });

        if (triggers == 0) {
            gameEngine.setTrigger(false);
        }
        else {
            gameEngine.setTrigger(true);
        }
    }
}