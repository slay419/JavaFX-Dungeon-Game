package unsw.dungeon;

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
        currentState = defaultState;
    }

    /**
     * This function will only be called if the player moves onto the same tile as the enemy
     */
    public void processEnemy(Player player) {
        interactWithPlayer(player);
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

    private void interactWithPlayer(Player player) {
        //currentState.interact(player);
        if (player.isInvincible()) {
            player.killEnemy(this);
        } else if (player.hasSword()) {
            player.useSword();
            player.killEnemy(this);
        } else {
            System.out.println("You died");
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

    /**
     * This function returns how far right the player is of the enemy
     * @param player
     * @return
     */
    public int playerXDistance(Player player) {
        int playerX = player.getX();
        int enemyX = this.getX();

        return playerX - enemyX;
    }

    /**
     * This function returns how far below the player is of the enemy
     * @param player
     * @return
     */
    public int playerYDistance(Player player) {
        int playerY = player.getY();
        int enemyY = this.getY();

        return playerY - enemyY;
    }

    public Boolean isAttackingPlayer(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        return playerX == this.getX() && playerY == this.getY();
    }
}