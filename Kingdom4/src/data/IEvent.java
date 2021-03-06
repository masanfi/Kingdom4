package data;

import javafx.geometry.Point2D;

/**
 *
 * This defines the structure of an event field, e.g. collision or trigger.
 * Copyright (c) 2018-2019 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public interface IEvent {
    public String getName();
    public Item getItem();
    public Point2D getCoordinates();
}
