package unsw.dungeon;

import java.io.IOException;
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
     * 
     * @param player
     * @return
     * @throws IOException
     */
    public Boolean reachedExit(Player player) throws IOException {
        int playerX = player.getX();
        int playerY = player.getY();
        if (playerX == this.getX() && playerY == this.getY()) {
            notifyObserver();
            player.showVictoryScreen();
            return true;
        }
        return false;
    }

    @Override
    public void process(Player player) {
        try {
            if (reachedExit(player)) {
                System.out.println("Reached the exit!");
            }
        } catch (IOException e) {
            e.printStackTrace();
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