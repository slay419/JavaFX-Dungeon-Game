package unsw.dungeon;

import java.util.ArrayList;

public class Exit extends Entity implements SubjectExit {

    private ArrayList<ObserverExit> observers = new ArrayList<ObserverExit>();

    public Exit(int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("exit");
    }

    /**
     * Detects when the player reaches the exit and notifies the observer if so
     * @param player
     * @return
     */
    public Boolean reachedExit(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        if (playerX == this.getX() && playerY == this.getY()) {
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
            o.updateExit();
        }

    }

}