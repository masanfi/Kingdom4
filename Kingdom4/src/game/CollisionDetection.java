package game;

import javafx.scene.shape.Rectangle;

/**
 *
 * This detects collisions in the game world.
 * Copyright (c) 2018-2019 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.2
 *
 */

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

        // Iterate through the list of collision objects
        gameEngine.getCollisionObject().forEach(collision->{
            object++;

            double minX = collision.getCoordinates().getX();
            double minY = collision.getCoordinates().getY();
            double width = gameEngine.getTileSize();
            double height = gameEngine.getTileSize();

            // get the "future" action square
            Rectangle actionSquareFuture = gameEngine.getActionSquareFuture();
            Rectangle actionRadius = new Rectangle(minX+1, minY+1, width, height);

            // check if action square collides with collision object
            if (actionRadius.getBoundsInParent().intersects(actionSquareFuture.boundsInParentProperty().getValue())) {
            	gameEngine.collisionCounter(object, collision.getItem());
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
