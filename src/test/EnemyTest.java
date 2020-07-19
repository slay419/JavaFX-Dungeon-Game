package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.InvincibilityPotion;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

public class EnemyTest {
    
    Dungeon d = new Dungeon(10, 1);
    Player player = new Player(d, 1, 1);
    Enemy enemy = new Enemy(d, 9, 1);
    

    @Test
    public void defaultEnemyStateTest() {
        d.addEntity(player);
        d.addEntity(enemy);

        assertEquals(enemy.getCurrentState(), "defaultState");

        // Enemy has not reached player
        assertFalse(enemy.isAttackingPlayer(player));

        // Check enemy has not died 
        assertTrue(d.entityExists(enemy));
    }

    @Test
    public void defaultEnemyStateTest1() {
        d.addEntity(player);
        enemy = new Enemy(d, 1, 1);
        d.addEntity(enemy);

        assertEquals(enemy.getCurrentState(), "defaultState");

        // Enemy is colliding with player in default state
        assertTrue(enemy.isAttackingPlayer(player));

        // Check enemy has not died 
        assertTrue(d.entityExists(enemy));
    }

    @Test
    public void defaultEnemyStateTest2() {
        d.addEntity(player);    // (1, 1)
        d.addEntity(enemy);     // (9, 1)

        assertEquals(enemy.getCurrentState(), "defaultState");

        player.moveRight();     // p: (2, 1)    e: (8, 1)

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 8);
        assertEquals(enemy.getY(), 1);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveRight();     // p: (3, 1)    e: (7, 1)

        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 7);
        assertEquals(enemy.getY(), 1);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveLeft();      // p: (2, 1)    e: (6, 1)

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 6);
        assertEquals(enemy.getY(), 1);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveRight();      // p: (3, 1)    e: (5, 1)

        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 5);
        assertEquals(enemy.getY(), 1);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveRight();      // p: (4, 1)    e: (4, 1)

        assertEquals(player.getX(), 4);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 4);
        assertEquals(enemy.getY(), 1);

        // Enemy has now reached the player
        
        // Enemy is colliding with player in default state
        assertTrue(enemy.isAttackingPlayer(player));

        // Check enemy has not died 
        assertTrue(d.entityExists(enemy));
        // Check player has died 
        assertFalse(d.entityExists(player));
    }

    @Test
    public void defaultStateAndSwordTest() {
        enemy = new Enemy(d, 5, 1);
        d.addEntity(player);    // (1, 1)
        d.addEntity(enemy);     // (5, 1)

        assertEquals(enemy.getCurrentState(), "defaultState");

        Sword sword = new Sword(2, 1);
        d.addEntity(sword);
        // Pick up sword
        player.moveRight();     // p: (2, 1)    e: (4, 1)

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 4);
        assertEquals(enemy.getY(), 1);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveRight();     // p: (3, 1)    e: (3, 1)

        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 1);
        
        // Enemy has now reached the player
        assertTrue(enemy.isAttackingPlayer(player));
    

        // Check enemy has died 
        assertFalse(d.entityExists(enemy));
        // Check player has not died 
        assertTrue(d.entityExists(player));
    }

    @Test 
    public void escapeStateTest() {
        enemy = new Enemy(d, 4, 1);
        d.addEntity(player);    // (1, 1)
        d.addEntity(enemy);     // (4, 1)

        assertEquals(enemy.getCurrentState(), "defaultState");

        InvincibilityPotion potion = new InvincibilityPotion(2, 1);
        d.addEntity(potion);
        // Pick up potion 
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 1);
        assertFalse(enemy.isAttackingPlayer(player));

        assertEquals(enemy.getCurrentState(), "escapeState");

        


    }



}