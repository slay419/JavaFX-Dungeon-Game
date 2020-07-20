package unsw.dungeon;

public interface SubjectEnemy {

    public void register(ObserverEnemy o);

    public void unregister(ObserverEnemy o);

    public void notifyObserver();
    
}