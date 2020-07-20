package unsw.dungeon;

import java.util.ArrayList;

public class Portal extends Entity {

    private int id;

    public Portal(int x, int y, int id) {
        super(x, y);
        this.id = id;    
        setImpassible(false);
        setName("portal");
    }

    public int getId() {
        return id;
    }

    /**
     * Teleports the player from one portal to the other matching portal 
     */
    @Override
    public void process(Player player) {
        Portal exit = findExit(player);
        if (exit == null) {
            return;
        }
        int exitX = exit.getX();
        int exitY = exit.getY();
        player.setXPos(exitX);
        player.setYPos(exitY);
    }

    /**
     * Loops through list of entities to find a matching portal with the same id
     */
    private Portal findExit(Player player) {
        ArrayList<Entity> portals = player.levelEntites("portal");
        for (Entity e : portals) {
            if (e.getId() == this.id && e != this) {
                return (Portal) e;
            }
        }
        return null;
    }
    
}