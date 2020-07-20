package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity implements SubjectEnemy {

    private Dungeon dungeon;
    private ArrayList<ObserverEnemy> observers = new ArrayList<ObserverEnemy>();

    // Tracks the state of the enemy
    private EnemyState defaultState;
    private EnemyState escapeState; 
    private EnemyState currentState;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("enemy");
        this.dungeon = dungeon;
        defaultState = new DefaultEnemyState(this);
        escapeState = new EscapeEnemyState(this);
        currentState = defaultState;
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
        if (currentState == defaultState) {
            return "defaultState";
        } else if (currentState == escapeState) {
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
            currentState = escapeState;
        } else {
            currentState = defaultState;
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
}