package unsw.dungeon;

import java.util.ArrayList;

public class Exit extends Entity implements SubjectExit {

    private ArrayList<ObserverExit> observers = new ArrayList<ObserverExit>();
    private Boolean playerReached; 

    public Exit(int x, int y) {
        super(x, y);
        playerReached = false;
        setImpassible(false);
        setName("exit");
    }

    public Boolean reachedExit(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        if (playerX == this.getX() && playerY == this.getY()) {
            System.out.println("vefore notifying observer");
            notifyObserver();
            return true;
        }
        return false;
    }

    @Override
    public void process(Player player) {
        if (reachedExit(player)) {
            System.out.println("Reached the exit!");
        }
    }

    @Override
    public void register(ObserverExit o) {
        observers.add(o);

    }

    @Override
    public void unregister(ObserverExit o) {
        observers.remove(o);

    }

    @Override
    public void notifyObserver() {
        for (ObserverExit o : observers) {
            o.update();
        }

    }

}