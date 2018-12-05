package game;

/**
 *
 * This defines the structure of the Observable class that implements the methods below.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public interface IObservable {
    void registerObserver(IObserver...observers);
    void removeObserver(IObserver observer);
    void notifyObservers();
}
