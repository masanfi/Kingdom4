package game;

import javafx.geometry.Point2D;

/**
 *
 * This defines the structure of an event field, e.g. collision or trigger.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public interface IEvent {
    public String getName();
    public Point2D getCoordinates();
}
