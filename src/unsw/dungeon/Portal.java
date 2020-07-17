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
            System.out.println("Could not find an exit portal");
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
    public Portal findExit(Player player) {
        ArrayList<Entity> portals = player.levelEntites("portal");
        for (Entity e : portals) {
            if (e.getId() == this.id && e != this) {
                System.out.println("Found a matching portal at: " + "(" + e.getX() + ", " + e.getY() + ")");
                return (Portal) e;
            }
        }
        return null;
    }
    
}