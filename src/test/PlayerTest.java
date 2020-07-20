package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
public class PlayerTest {

    Dungeon d = new Dungeon(10, 10);
    Player p = new Player(d, 1, 1);

    @Test
    public void movementTestEmptyUp(){
        d.addEntity(p);
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveUp();

        //Check the player is at 1, 0
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);
    }
    
    @Test
    public void movementTestEmptyDown(){
        d.addEntity(p);
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveDown();

        //Check the player is at 1, 2
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 2);
    }

    @Test
    public void movementTestEmptyLeft(){
        d.addEntity(p);
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveLeft();

        //Check the player is at 0, 1
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);
    }

    @Test
    public void movementTestEmptyRight(){
        d.addEntity(p);
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveRight();

        //Check the player is at 2, 1
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
    }
}