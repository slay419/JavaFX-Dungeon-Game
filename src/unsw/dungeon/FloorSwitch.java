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

    public void processSwitch(Player player){
        if (isTriggered()){
            System.out.println("Turning off switch");
            setTriggered(false);
            notifyObserver("increment");
        }
        else{
            System.out.println("Turning on switch");
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