package unsw.dungeon;

import unsw.dungeon.Enemy;
import unsw.dungeon.Player;

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
        int futureX = Integer.signum(diffX) + enemyX;
        int futureY = Integer.signum(diffY) + enemyY;
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

    public void interact(Player player) {
        System.out.println("Killed the player!");
    }


}