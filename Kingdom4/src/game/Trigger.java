package game;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * This allows to create a trigger object representing an action field in the game world that triggers a conversation or special event.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class Trigger implements IEvent {

    private String name;
    private Point2D coordinates;
    private boolean isWalkable;
    private boolean isPortable;
    private boolean isNpc;

    public Trigger(String name, Point2D coordinates, boolean isWalkable, boolean isPortable, boolean isNpc) {
        this.name = name;
        this.coordinates = coordinates;
        this.isWalkable = isWalkable;
        this.isPortable = isPortable;
        this.isNpc = isNpc;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Point2D getCoordinates() {
        return coordinates;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public boolean isPortable() {
        return isPortable;
    }

    public boolean isNpc() {
        return isNpc;
    }
}
