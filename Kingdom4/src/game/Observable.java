package game;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Implements the interface IObservable and provides methods to register and notify observers.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class Observable implements IObservable {
    List<IObserver> observers;

    public Observable() {
        observers = new LinkedList();
    }

    public void registerObserver(IObserver...observers){
        for(IObserver o: observers) {
            this.observers.add(o);
            System.out.println(o.getClass().getName() + " is active");
        }
    }

    public void removeObserver(IObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for(IObserver o: observers)
            o.update();
    }

}