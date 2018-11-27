package game;

import javafx.geometry.Point2D;

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
