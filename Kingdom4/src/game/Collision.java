package game;

import javafx.geometry.Point2D;

public class Collision {

    private String name;
    private Point2D coordinates;

    public Collision(String name, Point2D coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.name = type;
    }

    public Point2D getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2D coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates: " + this.coordinates.getX() + " | " + this.coordinates.getY();
    }
}
