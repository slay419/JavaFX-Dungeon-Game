package unsw.dungeon;

import unsw.dungeon.Enemy;
import unsw.dungeon.Player;

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
        int futureX = enemyX - Integer.signum(diffX); 
        int futureY = enemyY - Integer.signum(diffY);

        if (futureY == enemyY) {
            futureY++;
        }

        if (futureX == enemyX) {
            futureX++;
        }

        if (maxDiff > 0) {
            // Enemy is further along in x direction
            if (!player.checkImpassible(futureX, enemyY)) {
                // Move the enemy if it's not impassible
                enemy.setXPos(futureX);
                enemy.setYPos(enemyY);
            } else if (!player.checkImpassible(enemyX, futureY)) {
                // If the enemy cant move left/right, then move up/down
                enemy.setXPos(enemyX);
                enemy.setYPos(futureY);
            } 
        } else {
            if (!player.checkImpassible(enemyX, futureY)) {
                enemy.setXPos(enemyX);
                enemy.setYPos(futureY);
            } else if (!player.checkImpassible(futureX, enemyY)) {
                // Move the player if it's not impassible
                enemy.setXPos(futureX);
                enemy.setYPos(enemyY);
            } 
        }
        if (enemy.isAttackingPlayer(player)) {
            enemy.processEnemy(player);
        }
    }
    /*

    public void interact(Player player) {
        System.out.println("The player killed the enemy!");
        player.killEnemy(enemy);
    }
    */
}