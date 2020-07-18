package unsw;

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

        int maxDiff = Math.abs(diffX) - Math.abs(diffY);
        int futureX = enemyX - Integer.signum(diffX); 
        int futureY = enemyY - Integer.signum(diffY);
        if (maxDiff > 0) {
            // Enemy is further along in x direction 
            if (!player.checkImpassible(futureX, enemyY)) {
                // Move the player if it's not impassible
                enemy.setXPos(futureX);
                enemy.setYPos(enemyY);
            } else if (!player.checkImpassible(enemyX, futureY)) {
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
    }

    public void interact() {
        System.out.println("The player killed the enemy!");

    }
}