package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Inventory;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
public class DoorKeysTest {

    Dungeon d = new Dungeon(10, 10);
    Player p = new Player(d, 1, 1);
    //Door 1 is 2 spaces to the right of player and has an ID of 1
    Door d1 = new Door(3, 1, 1);
    Inventory i = new Inventory(p);
    
    //Walk into key with no key
    //Walk into key with key
    //Walk into door with no key
    //Walk into door with wrong key
    //Walk into door with correct key
    
    @Test
    public void pickUpKeyWithoutKey(){
        //Create a key to the right of player
        Key k = new Key(2, 1, 1);
        d.addEntity(k);
        d.addEntity(p);
        i = p.getInventory();
        //Check if inventory is empty
        assertFalse(i.hasKey());
        p.moveRight();
        
        //Check the key is in the inventory
        assertTrue(i.hasKey());
    }

    @Test
    public void pickUpKeyWithKey(){
        //Create key1 to the right of player
        Key k1 = new Key(2, 1, 1);
        //Create key2 to the right of key 1
        Key k2 = new Key(3, 1, 2);
        d.addEntity(k1);
        d.addEntity(k2);
        d.addEntity(p);
        i = p.getInventory();
        //Check if inventory is empty
        assertFalse(i.hasKey());
        p.moveRight();
        
        //Check the key is in the inventory
        assertTrue(i.checkKey(1));

        //Move onto the other key
        p.moveRight();

        //Check key in inventory ID is still 1
        assertTrue(i.checkKey(1));
    }

    @Test
    public void walkIntoDoorNoKey(){
        d.addEntity(d1);
        d.addEntity(p);

        //Check the door is closed
        assertTrue(d1.isImpassible());

        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveRight();

        //Check the player is at 2, 1
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);

        //This movement will attempt to open door
        p.moveRight();

        //Check the door is still closed
        assertTrue(d1.isImpassible());

        //Check the player is still at 2, 1
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);

        //This movement will attempt to move through door if opened
        p.moveRight();

        //Check the player is still at 2, 1
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);

    }
    
    @Test
    public void walkIntoDoorCorrectKey(){
        //Create key1 to the right of player
        Key k1 = new Key(2, 1, 1);
        d.addEntity(k1);
        d.addEntity(d1);
        d.addEntity(p);
        i = p.getInventory();
        //Check the door is closed
        assertTrue(d1.isImpassible());
        //Check inventory is empty
        assertFalse(i.hasKey());
    
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveRight();

        //Check the player is at 2, 1
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
        //Check if the key ID 1 is in inventory
        assertTrue(i.checkKey(1));

        //This movement will attempt to open door
        p.moveRight();

        //Check the door is open
        assertFalse(d1.isImpassible());
        //Check key has been consumed
        assertFalse(i.hasKey());

        //Check the player is still at 2, 1
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);

        //This movement will move through the door
        p.moveRight();

        //Check the player is still at 3, 1
        assertEquals(p.getX(), 3);
        assertEquals(p.getY(), 1);
    }

    @Test
    public void walkIntoDoorWrongKey(){
        //Create key1 to the right of player
        Key k1 = new Key(2, 1, 2);
        d.addEntity(k1);
        d.addEntity(d1);
        d.addEntity(p);
        i = p.getInventory();
        //Check the door is closed
        assertTrue(d1.isImpassible());
        //Check inventory is empty
        assertFalse(i.hasKey());
    
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveRight();

        //Check the player is at 2, 1
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
        //Check if the key ID 1 is in inventory
        assertTrue(i.checkKey(2));

        //This movement will attempt to open door
        p.moveRight();

        //Check the door is still closed
        assertTrue(d1.isImpassible());
        //Check key hasn't been consumed
        assertTrue(i.hasKey());

        //Check the player is still at 2, 1
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);

        //This movement will move into the door again (assumes it didn't open)
        p.moveRight();

        //Check the player is still at 2, 1
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
    }
}