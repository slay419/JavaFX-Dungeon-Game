package unsw.dungeon;

public interface SubjectTreasure {

    public void register(ObserverTreasure o);

    public void unregister(ObserverTreasure o);

    public void notifyObserver();
    
}