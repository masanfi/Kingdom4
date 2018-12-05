package game;

import javafx.geometry.Point2D;

/**
 *
 * This allows to create a collision object representing a non-walkable field or obstacle in the game world.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class Collision implements IEvent {

    private String name;
    private Point2D coordinates;

    public Collision(String name, Point2D coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public Point2D getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates: " + this.coordinates.getX() + " | " + this.coordinates.getY();
    }
}
