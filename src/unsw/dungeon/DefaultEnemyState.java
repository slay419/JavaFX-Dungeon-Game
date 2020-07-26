package unsw.dungeon;

public class DefaultEnemyState implements EnemyState {
    
    private Enemy enemy; 

    public DefaultEnemyState(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * In the default state, the enemy will move to player
     */
    public void move(Player player) {
        // Compare the player and enemy coordinates
        int playerX = player.getX();
        int playerY = player.getY();
        int enemyX = enemy.getX();
        int enemyY = enemy.getY();

        int diffX = playerX - enemyX;
        int diffY = playerY - enemyY;

        int maxDiff = Math.abs(diffX) - Math.abs(diffY);


        if (maxDiff > 0) {
            // Enemy wants to move along the x direction first before y direction 
            // Check if they should move left or right
            moveHorizontally(diffX, diffY);
        } else {
            moveVertically(diffX, diffY);
        } 

        if (enemy.isAttackingPlayer(player)) {
            enemy.processEnemy(player);
        }

    }

    /**
     * Checks horizontal movement first before moving vertically
     * @param diffX
     * @param diffY
     */
    private void moveHorizontally(int diffX, int diffY) {
        if (diffX > 0 && !enemy.moveRight()) {
            if (diffY > 0) {
                enemy.moveDown();
            } else if (diffY < 0) {
                enemy.moveUp();
            }
        } else if (diffX < 0 && !enemy.moveLeft()) {
            if (diffY > 0) {
                enemy.moveDown();
            } else if (diffY < 0) {
                enemy.moveUp();
            }
        }
    }

    private void moveVertically(int diffX, int diffY) {
        if (diffY > 0 && !enemy.moveDown()) {
            if (diffX > 0) {
                enemy.moveRight();
            } else if (diffX < 0) {
                enemy.moveLeft();
            }
        } else if (diffY < 0 && !enemy.moveUp()) {
            if (diffX > 0) {
                enemy.moveRight();
            } else if (diffX < 0) {
                enemy.moveLeft();
            }
        }
    }
}