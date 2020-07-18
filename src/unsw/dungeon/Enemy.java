package unsw.dungeon;

import unsw.DefaultEnemyState;
import unsw.EnemyState;
import unsw.EscapeEnemyState;

public class Enemy extends Entity {

    Dungeon dungeon;

    // Tracks the state of the player
    EnemyState defaultState;
    EnemyState escapeState; 

    EnemyState currentState;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("enemy");
        this.dungeon = dungeon;
        defaultState = new DefaultEnemyState(this);
        escapeState = new EscapeEnemyState(this);
        currentState = escapeState;
    }

    /**
     * This function will only be called if the player moves onto the same tile as the enemy
     */
    @Override
    public void process(Player player) {
        interactWithPlayer();
    }

    /**
     * Perform checks to see if the player invincible and change the state
     * @param player
     */
    private void setEnemyState(Player player) {
        if (player.isInvincible()) {
            currentState = escapeState;
        } else {
            currentState = defaultState;
        }
    }

    private void interactWithPlayer() {
        currentState.interact();
    }

    /**
     * Set the state before performing the appropriate move depending on the state
     * @param player
     */
    public void processMovement(Player player) {
        //setEnemyState(player);
        currentState.move(player);
    }

    public void moveUp() {
        if (getY() > 0) {
            this.setXPos(this.getX());
            this.setYPos(this.getY() - 1);
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            this.setXPos(this.getX());
            this.setYPos(this.getY() + 1);
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            this.setXPos(this.getX() - 1);
            this.setYPos(this.getY());
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            this.setXPos(this.getX() + 1);
            this.setYPos(this.getY());
        }
    }
}