package data;

import javafx.geometry.Point2D;

/**
 *
 * This allows to create a collision object representing a non-walkable field or obstacle in the game world.
 * Copyright (c) 2018-2019 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.2
 *
 */

public class Collision implements IEvent {

    private Item item;
    private Point2D coordinates;

    public Collision(Item item, Point2D coordinates) {
        this.item = item;
        this.coordinates = coordinates;
    }

    public Collision() {
		// TODO Auto-generated constructor stub
	}

	public Item getItem() {
        return item;
    }
	
	public String getName() {
        return item.getName();
    }

    public Point2D getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates: " + this.coordinates.getX() + " | " + this.coordinates.getY();
    }
}
