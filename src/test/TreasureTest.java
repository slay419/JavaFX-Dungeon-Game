package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Inventory;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;


public class TreasureTest {

    Dungeon d = new Dungeon(10, 10);
    Player player = new Player(d, 1, 1);
    

    @Test
    public void noTreasureTest() {
        d.addEntity(player);
        Inventory inventory = player.getInventory();
        assertEquals(inventory.countTreasure(), 0);
    }

    @Test
    public void oneTreasureTest() {
        Treasure treasure = new Treasure(1, 2);
        d.addEntity(player);
        Inventory inventory = player.getInventory();
        d.addEntity(treasure);
        assertEquals(inventory.countTreasure(), 0);

        player.moveDown();
        assertEquals(inventory.countTreasure(), 1);
    }

    @Test
    public void multipleTreasureTest() {
        Treasure treasure1 = new Treasure(1, 2);
        Treasure treasure2 = new Treasure(2, 2);
        d.addEntity(player);
        d.addEntity(treasure1);
        d.addEntity(treasure2);
        Inventory inventory = player.getInventory();
        assertEquals(inventory.countTreasure(), 0);

        player.moveDown();
        assertEquals(inventory.countTreasure(), 1);

        player.moveRight();
        assertEquals(inventory.countTreasure(), 2);
        
    }

}
