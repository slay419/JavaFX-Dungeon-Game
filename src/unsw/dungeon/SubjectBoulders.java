package unsw.dungeon;

public interface SubjectBoulders {

    public void register(ObserverBoulders o);

    public void unregister(ObserverBoulders o);

    public void notifyObserver(String string);
    
}