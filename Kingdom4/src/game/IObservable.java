package game;

public interface IObservable {
    void registerObserver(IObserver...observers);
    void removeObserver(IObserver observer);
    void notifyObservers();
}
