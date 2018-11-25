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

        gameEngine.getCollisionObject().forEach(collision->{
            double minX = collision.getCoordinates().getX();
            double minY = collision.getCoordinates().getY();
            double width = gameEngine.getTileSize();
            double height = gameEngine.getTileSize();

            Rectangle actionRadius = new Rectangle(minX-1, minY-1, width+2, height+2);


            if (actionRadius.getBoundsInParent().intersects(gameEngine.getActionSquare().boundsInParentProperty().getValue())) {
                System.out.println(collision.getName());
                collisions++;
            }
        });

        if (collisions == 0) {
            gameEngine.setCollision(false);
        }
        else {
            gameEngine.setCollision(true);
        }

    }
}
