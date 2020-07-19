package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

public class WallTest {
    //Creating a 10x10 dungeon and adding a player to (1, 1)
    Dungeon d = new Dungeon(10, 10);
    Player p = new Player(d, 1, 1);
    
    @Test
    public void movementTestEmptyUp(){
        //Create a wall above the player
        Wall w = new Wall(1, 0);
        d.addEntity(w);
        d.addEntity(p);
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveUp();

        //Check the player is at 1, 1 (Hasn't moved)
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
    }
    
    @Test
    public void movementTestEmptyDown(){
        //Create a wall below the player
        Wall w = new Wall(1, 2);
        d.addEntity(w);
        d.addEntity(p);
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveDown();

        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
    }

    @Test
    public void movementTestEmptyLeft(){
        //Create a wall left of the player
        Wall w = new Wall(0, 1);
        d.addEntity(w);
        d.addEntity(p);
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveLeft();

        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
    }

    @Test
    public void movementTestEmptyRight(){
        //Create a wall right of the player
        Wall w = new Wall(2, 1);
        d.addEntity(w);
        d.addEntity(p);
        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveRight();

        //Check the player is at 1, 1
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
    }
}