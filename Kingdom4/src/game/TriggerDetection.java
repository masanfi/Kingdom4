package game;

import javafx.scene.shape.Rectangle;

/**
 *
 * This detects triggers in the game world.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 0.9
 *
 */

public class TriggerDetection implements IObserver {

    GameEngine gameEngine;

    Trigger trigger;

    int triggers;
    int object;

    public TriggerDetection(GameEngine subject) {
        this.gameEngine = subject;
        this.gameEngine.registerObserver(this);
    }

    @Override
    public void update() {
        triggers = 0;
        object = 0;

        gameEngine.getTriggerObject().forEach(trigger->{
        	object++;
            double minX = trigger.getCoordinates().getX();
            double minY = trigger.getCoordinates().getY();
            double width = gameEngine.getTileSize();
            double height = gameEngine.getTileSize();

            Rectangle actionSquareFuture = gameEngine.getActionSquareFuture();
            Rectangle actionRadius = new Rectangle(minX+2, minY+2, width+4, height+4);

            if (actionRadius.getBoundsInParent().intersects(actionSquareFuture.boundsInParentProperty().getValue())) {
                
            	gameEngine.collisionCounter(object, trigger.getName());
            	
            	this.trigger = trigger;
                triggers++;
            }
        });

        if (triggers == 0) {
            gameEngine.checkForTriggers(null);
            gameEngine.setTrigger(false);
        }
        else {
            gameEngine.checkForTriggers(trigger);
            gameEngine.setTrigger(true);
        }
    }
}