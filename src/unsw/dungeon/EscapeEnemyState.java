package unsw.dungeon;

public class EscapeEnemyState implements EnemyState {

    Enemy enemy; 

    public EscapeEnemyState(Enemy enemy) {
        this.enemy = enemy;
    }
   
    /**
     * In the escape state, the enemy will run away from the player
     */
    public void move(Player player) {
        // Compare the player and enemy coordinates
        int playerX = player.getX();
        int playerY = player.getY();
        int enemyX = enemy.getX();
        int enemyY = enemy.getY();

        int diffX = playerX - enemyX;
        int diffY = playerY - enemyY;

        // Check to see if the enemy is further away in the x or y direction
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
        if (diffX > 0 && !enemy.moveLeft()) {
            if (diffY > 0) {
                enemy.moveUp();
            } else if (diffY < 0) {
                enemy.moveDown();
            } else if (diffY == 0 && !enemy.moveDown()) {
                enemy.moveUp();
            }
        } else if (diffX < 0 && !enemy.moveRight()) {
            if (diffY > 0) {
                enemy.moveUp();
            } else if (diffY < 0) {
                enemy.moveDown();
            } else if (diffY == 0 && !enemy.moveDown()) {
                enemy.moveUp();
            }
        }
    }

    private void moveVertically(int diffX, int diffY) {
        if (diffY > 0 && !enemy.moveUp()) {
            if (diffX > 0) {
                enemy.moveLeft();
            } else if (diffX < 0) {
                enemy.moveRight();
            } else if (diffX == 0 && !enemy.moveLeft()) {
                enemy.moveRight();
            }
        } else if (diffY < 0 && !enemy.moveDown()) {
            if (diffX > 0) {
                enemy.moveLeft();
            } else if (diffX < 0) {
                enemy.moveRight();
            } else if (diffX == 0 && !enemy.moveLeft()) {
                enemy.moveRight();
            }
        }
    }
}