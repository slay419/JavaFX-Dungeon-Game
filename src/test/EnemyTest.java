package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.InvincibilityPotion;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;

public class EnemyTest {
    
    Dungeon d = new Dungeon(10, 1);

    Wall wall1 = new Wall(10, 1);
    Wall wall2 = new Wall(9, 1);
    Wall wall3 = new Wall(8, 1);
    Wall wall4 = new Wall(7, 1);
    Wall wall5 = new Wall(6, 1);
    Wall wall6 = new Wall(5, 1);
    Wall wall7 = new Wall(4, 1);
    Wall wall8 = new Wall(3, 1);
    Wall wall9 = new Wall(2, 1);
    Wall wall10 = new Wall(1, 1);

    Wall wall11 = new Wall(10, 3);
    Wall wall12 = new Wall(9, 3);
    Wall wall13 = new Wall(8, 3);
    Wall wall14 = new Wall(7, 3);
    Wall wall15 = new Wall(6, 3);
    Wall wall16 = new Wall(5, 3);
    Wall wall17 = new Wall(4, 3);
    Wall wall18 = new Wall(3, 3);
    Wall wall19 = new Wall(3, 3);
    Wall wall20 = new Wall(1, 3);

    Wall wall21 = new Wall(1, 2);
    Wall wall22 = new Wall(10, 2);
    
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
        player.moveRight();     // p: (2, 1)    e: (5, 1)

        assertEquals(enemy.getCurrentState(), "escapeState");

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 5);
        assertEquals(enemy.getY(), 1);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveRight();     // p: (3, 1)    e: (6, 1)

        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 6);
        assertEquals(enemy.getY(), 1);

        player.moveLeft();      // p: (2, 1)    e: (7, 1)

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 7);
        assertEquals(enemy.getY(), 1);
    }

    @Test 
    public void escapeStateCollisionTest() {
        d = new Dungeon(10, 3);
        enemy = new Enemy(d, 9, 2);
        player = new Player(d, 6, 2);
        d.addEntity(player);    // (6, 2)
        d.addEntity(enemy);     // (9, 2)

        // Adding walls to box enemy in
        d.addEntity(wall1);
        d.addEntity(wall2);
        d.addEntity(wall3);
        d.addEntity(wall4);
        d.addEntity(wall5);
        d.addEntity(wall6);
        d.addEntity(wall7);
        d.addEntity(wall8);
        d.addEntity(wall9);
        d.addEntity(wall10);

        d.addEntity(wall11);
        d.addEntity(wall12);
        d.addEntity(wall13);
        d.addEntity(wall14);
        d.addEntity(wall15);
        d.addEntity(wall16);
        d.addEntity(wall17);
        d.addEntity(wall18);
        d.addEntity(wall19);
        d.addEntity(wall20);
        d.addEntity(wall21);
        d.addEntity(wall22);

        assertEquals(enemy.getCurrentState(), "defaultState");

        InvincibilityPotion potion = new InvincibilityPotion(7, 2);
        d.addEntity(potion);
        // Pick up potion 
        player.moveRight();     // p: (7, 2)    e: (9, 2)

        assertEquals(enemy.getCurrentState(), "escapeState");

        assertEquals(player.getX(), 7);
        assertEquals(player.getY(), 2);
        assertEquals(enemy.getX(), 9);
        assertEquals(enemy.getY(), 2);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveRight();     // p: (8, 2)    e: (9, 2)      enemy cant move anymore
        assertEquals(player.getX(), 8);
        assertEquals(player.getY(), 2);
        assertEquals(enemy.getX(), 9);
        assertEquals(enemy.getY(), 2);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveLeft();     // p: (7, 2)    e: (9, 2)      enemy cant move anymore
        assertEquals(player.getX(), 7);
        assertEquals(player.getY(), 2);
        assertEquals(enemy.getX(), 9);
        assertEquals(enemy.getY(), 2);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveRight();     // p: (8, 2)    e: (9, 2)      enemy cant move anymore
        assertEquals(player.getX(), 8);
        assertEquals(player.getY(), 2);
        assertEquals(enemy.getX(), 9);
        assertEquals(enemy.getY(), 2);
        assertFalse(enemy.isAttackingPlayer(player));

        player.moveRight();     // p: (9, 2)    e: (9, 2)      enemy cant move anymore
        assertEquals(player.getX(), 9);
        assertEquals(player.getY(), 2);
        assertEquals(enemy.getX(), 9);
        assertEquals(enemy.getY(), 2);
        assertTrue(enemy.isAttackingPlayer(player));

        // Check enemy has died 
        assertFalse(d.entityExists(enemy));
        // Check player has not died 
        assertTrue(d.entityExists(player));
    }

    @Test 
    public void testingCollisionSwitchTest() {
        enemy = new Enemy(d, 8, 2);
        player = new Player(d, 1, 2);
        d.addEntity(player);    // (1, 2)
        d.addEntity(enemy);     // (8, 2)

        // can walk through floorswitch
        FloorSwitch floorSwitch = new FloorSwitch(7, 2);

        d.addEntity(floorSwitch);

        player.moveLeft();  // Can pass through floorswitch 

        assertEquals(enemy.getX(), 7);
        assertEquals(enemy.getY(), 2);

    }

    @Test 
    public void testingCollisionBoulderTest() {
        enemy = new Enemy(d, 8, 2);
        player = new Player(d, 1, 2);
        d.addEntity(player);    // (1, 2)
        d.addEntity(enemy);     // (8, 2)

        // can walk through boulder
        Boulder boulder = new Boulder(7, 2);

        d.addEntity(boulder);

        player.moveLeft();  // Cant pass through boulder

        assertEquals(enemy.getX(), 8);
        assertEquals(enemy.getY(), 2);

    }

    @Test 
    public void testingCollisionDoorTest() {
        enemy = new Enemy(d, 8, 2);
        player = new Player(d, 1, 2);
        d.addEntity(player);    // (1, 2)
        d.addEntity(enemy);     // (8, 2)

        // can walk through door
        Door door = new Door(7, 2, 1);

        d.addEntity(door);

        player.moveLeft();  // Cant pass through door

        assertEquals(enemy.getX(), 8);
        assertEquals(enemy.getY(), 2);

    }

    @Test 
    public void testingCollisionKeyTest() {
        enemy = new Enemy(d, 8, 2);
        player = new Player(d, 1, 2);
        d.addEntity(player);    // (1, 2)
        d.addEntity(enemy);     // (8, 2)

        // can walk through key
        Key key = new Key(7, 2, 1);

        d.addEntity(key);

        player.moveLeft();  // Can pass through key

        assertEquals(enemy.getX(), 7);
        assertEquals(enemy.getY(), 2);

    }

    @Test 
    public void testingCollisionPortalTest() {
        enemy = new Enemy(d, 8, 2);
        player = new Player(d, 1, 2);
        d.addEntity(player);    // (1, 2)
        d.addEntity(enemy);     // (8, 2)

        // can walk through portal
        Portal portal = new Portal(7, 2, 1);

        d.addEntity(portal);

        player.moveLeft();  // Can pass through portal but won't use it

        assertEquals(enemy.getX(), 7);
        assertEquals(enemy.getY(), 2);

    }

    @Test 
    public void testingCollisionSwordTest() {
        enemy = new Enemy(d, 8, 2);
        player = new Player(d, 1, 2);
        d.addEntity(player);    // (1, 2)
        d.addEntity(enemy);     // (8, 2)

        // can walk through sword
        Sword sword = new Sword(7, 2);

        d.addEntity(sword);

        player.moveLeft();  // Can pass through sword but won't use it

        assertEquals(enemy.getX(), 7);
        assertEquals(enemy.getY(), 2);
    }

    @Test 
    public void testingCollisionPotionTest() {
        enemy = new Enemy(d, 8, 2);
        player = new Player(d, 1, 2);
        d.addEntity(player);    // (1, 2)
        d.addEntity(enemy);     // (8, 2)

        // can walk through potion
        InvincibilityPotion potion = new InvincibilityPotion(7, 2);

        d.addEntity(potion);

        player.moveLeft();  // Can pass through potion but won't use it

        assertEquals(enemy.getX(), 7);
        assertEquals(enemy.getY(), 2);
    }

    @Test 
    public void testingCollisionEnemyTest() {
        d = new Dungeon(10, 3);
        Enemy enemy1 = new Enemy(d, 8, 2);
        Enemy enemy2 = new Enemy(d, 7, 2);
        player = new Player(d, 1, 2);
        d.addEntity(player);    // (1, 2)
        d.addEntity(enemy1);     // (8, 2)
        d.addEntity(enemy2);

        InvincibilityPotion potion = new InvincibilityPotion(2, 2);
        d.addEntity(potion);

        // Adding walls to box enemy in
        d.addEntity(wall1);
        d.addEntity(wall2);
        d.addEntity(wall3);
        d.addEntity(wall4);
        d.addEntity(wall5);
        d.addEntity(wall6);
        d.addEntity(wall7);
        d.addEntity(wall8);
        d.addEntity(wall9);
        d.addEntity(wall10);

        d.addEntity(wall11);
        d.addEntity(wall12);
        d.addEntity(wall13);
        d.addEntity(wall14);
        d.addEntity(wall15);
        d.addEntity(wall16);
        d.addEntity(wall17);
        d.addEntity(wall18);
        d.addEntity(wall19);
        d.addEntity(wall20);
        d.addEntity(wall21);
        d.addEntity(wall22);


        player.moveRight(); // walk through potion and get enemies to walk towards wall

        assertEquals(enemy1.getX(), 9);
        assertEquals(enemy1.getY(), 2);

        assertEquals(enemy2.getX(), 8);
        assertEquals(enemy2.getY(), 2);

        player.moveRight();

        // Enemies can be on the same tile as each other but wont kill each other 
        assertEquals(enemy1.getX(), 9);
        assertEquals(enemy1.getY(), 2);

        assertEquals(enemy2.getX(), 9);
        assertEquals(enemy2.getY(), 2);

        assertTrue(d.entityExists(enemy1));
        assertTrue(d.entityExists(enemy2));
    }





}