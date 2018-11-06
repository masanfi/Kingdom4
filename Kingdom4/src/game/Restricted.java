package game;

import javafx.geometry.Point2D;

class Restricted
{

    private final String info;
    private Point2D cord;

    Restricted(String info, Point2D cord)
    {
        this.info = info;
        this.cord = cord;
    }

    String getInfo()
    {
        return info;
    }

    Point2D getPoint()
    {
        return cord;
    }

}