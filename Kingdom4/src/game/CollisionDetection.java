package game;

import javafx.scene.shape.Rectangle;

public class CollisionDetection implements IObserver {

    GameEngine gameEngine;

    int collisions;
    int object;

    public CollisionDetection(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.gameEngine.registerObserver(this);
    }

    @Override
    public void update() {
        collisions = 0;
        object = 0;

        gameEngine.getCollisionObject().forEach(collision->{
            object++;

            double minX = collision.getCoordinates().getX();
            double minY = collision.getCoordinates().getY();
            double width = gameEngine.getTileSize();
            double height = gameEngine.getTileSize();
            
            Rectangle actionSquareFuture = gameEngine.getActionSquareFuture();
            Rectangle actionRadius = new Rectangle(minX+1, minY+1, width, height);

            if (actionRadius.getBoundsInParent().intersects(actionSquareFuture.boundsInParentProperty().getValue())) {
            	gameEngine.collisionCounter(object, collision.getName());
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
