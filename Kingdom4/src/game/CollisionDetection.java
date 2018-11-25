package game;

import javafx.scene.shape.Rectangle;

public class CollisionDetection implements IObserver {

    GameEngine gameEngine;

    boolean isCollision = false;
    int collisions;
    int objects;

    public CollisionDetection(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.gameEngine.registerObserver(this);
    }

    @Override
    public void update() {
        collisions = 0;

        gameEngine.getObstacle().forEach(obstacle->{
            double minX = obstacle.boundsInParentProperty().getValue().getMinX();
            double minY = obstacle.boundsInParentProperty().getValue().getMinY();
            double width = obstacle.boundsInParentProperty().getValue().getWidth();
            double height = obstacle.boundsInParentProperty().getValue().getHeight();

            Rectangle actionRadius = new Rectangle(minX-1, minY-1, width+2, height+2);
            //System.out.println(actionRadius.getBoundsInParent().intersects(gameEngine.getActionSquare().boundsInParentProperty().getValue()));

            if (actionRadius.getBoundsInParent().intersects(gameEngine.getActionSquare().boundsInParentProperty().getValue())) {
                collisions++;
                //System.out.println(collisions);
            }
            //isCollision = actionRadius.getBoundsInParent().intersects(gameEngine.getActionSquare().boundsInParentProperty().getValue());
        });

        if (collisions == 0) {
            gameEngine.setCollision(false);
        }
        else {
            gameEngine.setCollision(true);
        }

    }
}
