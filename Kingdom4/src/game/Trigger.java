package game;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Trigger implements IEvent {

    private String name;
    private Point2D coordinates;

    public Trigger(String name, Point2D coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Point2D getCoordinates() {
        return coordinates;
    }
}
