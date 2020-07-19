package unsw.dungeon;

public interface SubjectExit {

    public void register(ObserverExit o);

    public void unregister(ObserverExit o);

    public void notifyObserver();
}