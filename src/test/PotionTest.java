package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Enemy;
import unsw.dungeon.Inventory;
import unsw.dungeon.InvincibilityPotion;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;

public class PotionTest {

    Dungeon d = new Dungeon(10, 10);
    Player player = new Player(d, 5, 5);

    @Test
    public void noPotionTest() {
        d.addEntity(player);
        Inventory inventory = player.getInventory();
        assertEquals(inventory.getPotion(), null);
    }

    @Test
    public void pickupPotionTest() {
        d.addEntity(player);
        Inventory inventory = player.getInventory();
        assertEquals(inventory.getPotion(), null);

        InvincibilityPotion potion = new InvincibilityPotion(6, 5);
        d.addEntity(potion);
        player.moveRight();
        assertEquals(inventory.getPotion(), potion);
    }

    @Test
    public void potionChargesTest() {
        d.addEntity(player);
        Inventory inventory = player.getInventory();
        InvincibilityPotion potion = new InvincibilityPotion(6, 5);
        d.addEntity(potion);

        player.moveRight();
        assertEquals(inventory.getPotion(), potion);
        assertEquals(inventory.potionCharges(), 10);

        player.moveRight(); 
        assertEquals(inventory.potionCharges(), 9);

        player.moveLeft();
        assertEquals(inventory.potionCharges(), 8);

        player.moveUp();
        assertEquals(inventory.potionCharges(), 7);

        player.moveDown();
        assertEquals(inventory.potionCharges(), 6);

        player.moveRight(); 
        assertEquals(inventory.potionCharges(), 5);

        player.moveLeft();
        assertEquals(inventory.potionCharges(), 4);

        player.moveUp();
        assertEquals(inventory.potionCharges(), 3);

        player.moveDown();
        assertEquals(inventory.potionCharges(), 2);

        player.moveRight(); 
        assertEquals(inventory.potionCharges(), 1);

        player.moveLeft();
        assertEquals(inventory.getPotion(), null);
    }

    @Test 
    public void moveImpassibleEntityTest() {
        d.addEntity(player);
        Inventory inventory = player.getInventory();
        InvincibilityPotion potion = new InvincibilityPotion(6, 5);
        d.addEntity(potion);

        Wall wall = new Wall(7, 5);
        d.addEntity(wall);

        player.moveRight();
        assertEquals(inventory.getPotion(), potion);
        assertEquals(inventory.potionCharges(), 10);

        player.moveRight(); // move into wall 
        assertEquals(inventory.potionCharges(), 10);

        player.moveUp();
        assertEquals(inventory.potionCharges(), 9);

        player.moveRight();
        assertEquals(inventory.potionCharges(), 8);

        player.moveDown(); // move into wall again
        assertEquals(inventory.potionCharges(), 8);
    }

    @Test 
    public void pickupMultiplePotionsTest() {
        d.addEntity(player);
        Inventory inventory = player.getInventory();
        InvincibilityPotion potion1 = new InvincibilityPotion(6, 5);
        d.addEntity(potion1);

        InvincibilityPotion potion2 = new InvincibilityPotion(8, 5);
        d.addEntity(potion2);

        player.moveRight(); // pickup first potion 
        assertEquals(inventory.getPotion(), potion1);
        assertEquals(potion1.getCharges(), 10);

        player.moveRight();
        assertEquals(inventory.potionCharges(), 9);

        player.moveRight();    // move into second potion - replaced first one 
        assertEquals(inventory.getPotion(), potion2);
        assertEquals(inventory.potionCharges(), 10);

        player.moveRight();  
        assertEquals(inventory.potionCharges(), 9);

        player.moveDown();  
        assertEquals(inventory.potionCharges(), 8);

    }

}