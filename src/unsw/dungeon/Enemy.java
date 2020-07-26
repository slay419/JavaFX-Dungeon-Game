package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity implements SubjectEnemy {

    private Dungeon dungeon;
    private ArrayList<ObserverEnemy> observers = new ArrayList<ObserverEnemy>();

    // Tracks the state of the enemy
    private EnemyState currentState;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("enemy");
        this.dungeon = dungeon;
        currentState = new DefaultEnemyState(this);
    }

    /**
     * This function will only be called if the player moves onto the same tile as the enemy
     */
    public void processEnemy(Player player) {
        if (player.isInvincible()) {
            player.killEnemy(this);
            notifyObserver();
        } else if (player.hasSword()) {
            player.useSword();
            player.killEnemy(this);
            notifyObserver();
        } else {
            System.out.println("You died");
            dungeon.removeEntity(player);
        }
    }   

    public String getCurrentState() {
        if (currentState instanceof DefaultEnemyState) {
            return "defaultState";
        } else if (currentState instanceof EscapeEnemyState) {
            return "escapeState";
        }
        return "";
    }

    /**
     * Perform checks to see if the player invincible and change the state
     * @param player
     */
    private void setEnemyState(Player player) {
        if (player.isInvincible()) {
            System.out.println("Changed enemy to escapeState");
            currentState = new EscapeEnemyState(this);
        } else {
            currentState = new DefaultEnemyState(this);
        }
    }

    /**
     * Set the state before performing the appropriate move depending on the state
     * @param player
     */
    public void processMovement(Player player) {
        setEnemyState(player);
        currentState.move(player);
    }

    public Boolean isAttackingPlayer(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        return playerX == this.getX() && playerY == this.getY();
    }

    @Override
    public void register(ObserverEnemy o) {
        observers.add(o);

    }

    @Override
    public void unregister(ObserverEnemy o) {
        observers.remove(o);

    }

    @Override
    public void notifyObserver() {
        for (ObserverEnemy o : observers) {
            o.updateEnemy();
        }
    }


    public Boolean moveUp() {
        if (getY() > 0 && !checkImpassible(this.getX(), this.getY() - 1)) {
            this.setXPos(this.getX());
            this.setYPos(this.getY() - 1);
            return true;
        }
        return false;
    }

    public Boolean moveDown() {
        if (getY() < dungeon.getHeight() - 1 && !checkImpassible(this.getX(), this.getY() + 1)) {
            this.setXPos(this.getX());
            this.setYPos(this.getY() + 1);
            return true;
        }
        return false;
    }

    public Boolean moveLeft() {
        if (getX() > 0 && !checkImpassible(this.getX() - 1, this.getY())) {
            this.setXPos(this.getX() - 1);
            this.setYPos(this.getY());
            return true;
        }
        return false;
    }

    public Boolean moveRight() {
        if (getX() < dungeon.getWidth() - 1 && !checkImpassible(this.getX() + 1, this.getY())) {
            this.setXPos(this.getX() + 1);
            this.setYPos(this.getY());
            return true;
        }
        return false;
    }

    /**
     * Checks the coordinates to see if the entity there is impassible and allows 
     * player movement
     * @param x
     * @param y
     * @return
     */
    public Boolean checkImpassible(int x, int y) {
        ArrayList<Entity> entityList = dungeon.getEntityList(x, y);
        if (entityList == null) {
            return false;
        }
        for (Entity e : entityList) {
            if (e.isImpassible() || e instanceof Enemy) {
                return true;
            }
        }
        return false;
    }
}