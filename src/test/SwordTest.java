package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Inventory;
import unsw.dungeon.InvincibilityPotion;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;
public class SwordTest {

    Dungeon d = new Dungeon(10, 10);
    Player p = new Player(d, 1, 1);
    Inventory i = new Inventory(p);
    
    //Pick up Sword with no Sword
    //Pick up Sword with Sword
    //Walk into enemy with Sword (check charges)
    //Walk into enemy with Sword with Potion
    
    @Test
    public void pickUpSwordWithoutSword(){
        //Create a sword to the right of player
        Sword s = new Sword(2, 1);
        d.addEntity(s);
        d.addEntity(p);
        i = p.getInventory();
        //Check if inventory is empty
        assertFalse(i.hasSword());
        p.moveRight();
        
        //Check the key is in the inventory
        assertTrue(i.hasSword());
    }

    @Test
    public void pickUpSwordWithSword(){
        //Create sword1 to the right of player
        Sword s1 = new Sword(2, 1);
        //Create sword2 to the right of sword1
        Sword s2 = new Sword(3, 1);
        d.addEntity(s1);
        d.addEntity(s2);
        d.addEntity(p);
        i = p.getInventory();
        //Check if inventory is empty
        assertFalse(i.hasSword());
        p.moveRight();
        
        //Check sword has no charges used
        assertEquals(s1.getCharges(), 5);

        //Consume a charge of the sword
        s1.consumeCharge();

        //Check sword now has 4 charges
        assertEquals(s1.getCharges(), 4);

        //Move onto the other sword
        p.moveRight();

        //Check sword in inventory is the same one (still 4 charges)
        assertEquals(s1.getCharges(), 4);
    }

    @Test
    public void walkIntoEnemyWithSword(){
        Enemy enemy = new Enemy(d, 4, 1);
        Sword s = new Sword(2, 1);
        d.addEntity(enemy);
        d.addEntity(p);
        d.addEntity(s);
        i = p.getInventory();

        //Pick up the sword
        p.moveRight();

        //Check player has sword
        assertTrue(i.hasSword());

        //Check charges of sword is max (5)
        assertEquals(s.getCharges(), 5);

        //This movement will kill the enemy
        p.moveRight();

        assertEquals(s.getCharges(), 4);
    }

    @Test
    public void useAllSwordCharges(){
        Enemy enemy1 = new Enemy(d, 4, 1);
        Enemy enemy2 = new Enemy(d, 5, 1);
        Enemy enemy3 = new Enemy(d, 6, 1);
        Enemy enemy4 = new Enemy(d, 7, 1);
        Enemy enemy5 = new Enemy(d, 8, 1);
        Sword s = new Sword(2, 1);
        d.addEntity(enemy1);
        d.addEntity(enemy2);
        d.addEntity(enemy3);
        d.addEntity(enemy4);
        d.addEntity(enemy5);
        d.addEntity(p);
        d.addEntity(s);
        i = p.getInventory();

        //Pick up the sword
        p.moveRight();

        //Check player has sword
        assertTrue(i.hasSword());

        //Check charges of sword is max (5)
        assertEquals(s.getCharges(), 5);

        //This movement will kill the first enemy (and cause the
        //second enemy to kill itself)
        p.moveRight();

        //Check player has sword
        assertTrue(i.hasSword());

        //Check charges is now 3
        assertEquals(s.getCharges(), 3);

        //This movement will kill the third enemy (and cause the
        //fourth enemy to kill itself)
        p.moveRight();

        //Check player has sword
        assertTrue(i.hasSword());

        //Check charges is now 1
        assertEquals(s.getCharges(), 1);

        //Check player has sword
        assertTrue(i.hasSword());

        //This movement will kill the last enemy
        p.moveRight();

        //Check charges is now 0
        assertEquals(s.getCharges(), 0);

        //Check player doesn't have the sword
        assertFalse(i.hasSword());
    }

    @Test
    public void walkIntoEnemyWithSwordAndPotion(){
        //Walls to prevent enemy from running away
        Wall w1 = new Wall (6, 1);
        Wall w2 = new Wall (4, 0);
        Wall w3 = new Wall (4, 2);
        Wall w4 = new Wall (3, 0);
        Wall w5 = new Wall (3, 2);
        Wall w6 = new Wall (5, 0);
        Wall w7 = new Wall (5, 2);

        
        Enemy enemy = new Enemy(d, 5, 1);
        Sword s = new Sword(3, 1);
        InvincibilityPotion potion = new InvincibilityPotion(2, 1);
        d.addEntity(enemy);
        d.addEntity(potion);
        d.addEntity(p);
        d.addEntity(s);
        d.addEntity(w1);
        d.addEntity(w2);
        d.addEntity(w3);
        d.addEntity(w4);
        d.addEntity(w5);
        d.addEntity(w6);
        d.addEntity(w7);

        i = p.getInventory();

        //Pick up the potion
        p.moveRight();

        //Check player has potion
        assertTrue(i.hasPotion());

        //This movement picks up Sword
        p.moveRight();

        //Check player has sword
        assertTrue(i.hasSword());

        //Check charges of sword is max (5)
        assertEquals(s.getCharges(), 5);


        //This movement moves towards enemy
        p.moveRight();

        //This movement will kill the enemy
        p.moveRight();

        //Sword charge shouldn't be consumed
        assertEquals(s.getCharges(), 5);
    }

}