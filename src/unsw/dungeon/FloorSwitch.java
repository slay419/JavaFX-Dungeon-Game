package unsw.dungeon;

import java.util.ArrayList;

public class FloorSwitch extends Entity implements SubjectBoulders {
    
    private boolean triggered;

    private ArrayList<ObserverBoulders> observers = new ArrayList<ObserverBoulders>();

    public FloorSwitch(int x, int y){
        super(x, y);
        setImpassible(false);
        setName("floorSwitch");
        this.triggered = false;
    }

    //Process Switch sets a switch to triggered if it isn't, and decrements the observers count.
    //If the switch is already triggered it will be untriggered and the observer count incremented.
    public void processSwitch(){
        if (isTriggered()){
            setTriggered(false);
            notifyObserver("increment");
        }
        else{
            setTriggered(true);
            notifyObserver("decrement");
        }
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

    @Override
    public void register(ObserverBoulders o) {
        observers.add(o);

    }

    @Override
    public void unregister(ObserverBoulders o) {
        observers.remove(o);

    }

    @Override
    public void notifyObserver(String string) {
        for (ObserverBoulders o : observers) {
            o.update(string);
        }

    }
}