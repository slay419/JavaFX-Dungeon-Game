package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;

public class PortalTest {
    
    Dungeon d = new Dungeon(10, 10);
    Player player = new Player(d, 1, 1);

    Portal portalOneEntry = new Portal(1, 2, 0);
    Portal portalOneExit = new Portal(8, 8, 0);

    Portal portalTwoEntry = new Portal(2, 1, 1);
    Portal portalTwoExit = new Portal(9, 9, 1);


    @Test
    public void walkIntoPortalOne() {
        d.addEntity(player);
        d.addEntity(portalOneEntry);
        d.addEntity(portalOneExit);

        // Check same position as portal exit 
        player.moveDown();
        assertEquals(player.getX(), 8);
        assertEquals(player.getY(), 8);      
    }

    @Test
    public void walkIntoPortalOneExit() {
        d.addEntity(player);
        d.addEntity(portalOneEntry);
        d.addEntity(portalOneExit);

        // Check same position as portal exit 
        player.moveDown();
        assertEquals(player.getX(), 8);
        assertEquals(player.getY(), 8);      

        // Move back into the portal and check same position as portal entrance 
        player.moveRight();
        player.moveLeft();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);  
    }

    @Test
    public void walkIntoPortalOneExit2() {
        d.addEntity(player);
        d.addEntity(portalOneEntry);
        d.addEntity(portalOneExit);

        // Check same position as portal exit 
        player.moveDown();
        assertEquals(player.getX(), 8);
        assertEquals(player.getY(), 8);      

        // Move back into the portal and check same position as portal entrance 
        player.moveUp();
        player.moveDown();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);  
    }

    @Test
    public void walkIntoPortalOneExit3() {
        d.addEntity(player);
        d.addEntity(portalOneEntry);
        d.addEntity(portalOneExit);

        // Check same position as portal exit 
        player.moveDown();
        assertEquals(player.getX(), 8);
        assertEquals(player.getY(), 8);      

        // Move back into the portal and check same position as portal entrance 
        player.moveLeft();
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);  
    }

    @Test
    public void walkIntoPortalOneExit4() {
        d.addEntity(player);
        d.addEntity(portalOneEntry);
        d.addEntity(portalOneExit);

        // Check same position as portal exit 
        player.moveDown();
        assertEquals(player.getX(), 8);
        assertEquals(player.getY(), 8);      

        // Move back into the portal and check same position as portal entrance 
        player.moveDown();
        player.moveUp();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);  
    }

    @Test
    public void walkIntoPortalTwo() {
        d.addEntity(player);
        d.addEntity(portalOneEntry);
        d.addEntity(portalOneExit);
        d.addEntity(portalTwoEntry);
        d.addEntity(portalTwoExit);

        // Check same position as portal two exit NOT portal 1
        player.moveRight();
        assertEquals(player.getX(), 9);
        assertEquals(player.getY(), 9);      

        // Move back into the portal and check same position as portal entrance 
        player.moveLeft();
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);  
    }

}