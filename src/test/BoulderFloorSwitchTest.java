package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.Exit;
import unsw.dungeon.Treasure;
import unsw.dungeon.Door;
import unsw.dungeon.Key;
import unsw.dungeon.Portal;
import unsw.dungeon.Sword;
import unsw.dungeon.InvincibilityPotion;
import unsw.dungeon.Enemy;
import unsw.dungeon.Boulder;
import unsw.dungeon.FloorSwitch;
public class BoulderFloorSwitchTest {

    Dungeon d = new Dungeon(10, 10);
    Player p = new Player(d, 1, 1);
    Boulder b = new Boulder(2, 1);
    //Pushing boulder into empty space
    //Pushing boulder into another entity
    //Pushing boulder into floorswitch
    //Pushing boulder off floorswitch
    //Player walk on floorswitch
    //Enemy walk on floorswitch

    @Test
    public void pushBoulderOntoEmptySpace(){
        d.addEntity(p);
        d.addEntity(b);

        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);

        //Check space to the right of boulder is empty
        assertEquals(d.getEntity(3, 1), null);

        //Should push boulder right, player shouldn't move
        p.moveRight();

        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        //Check the boulder is has been moved
        assertEquals(b.getX(), 3);
        assertEquals(b.getY(), 1);
    }

    @Test
    public void pushBoulderOntoWall(){
        d.addEntity(p);
        d.addEntity(b);
        Wall w = new Wall(3, 1);
        d.addEntity(w);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);

        //Check wall is at 3, 1
        assertEquals(w.getX(), 3);
        assertEquals(w.getY(), 1);

        //Should push boulder right, player shouldn't move
        p.moveRight();

        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }

    
    @Test
    public void pushBoulderOntoExit(){
        d.addEntity(p);
        d.addEntity(b);
        Exit e = new Exit(3, 1);
        d.addEntity(e);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        
        //Check exit is at 3, 1
        assertEquals(e.getX(), 3);
        assertEquals(e.getY(), 1);
        
        //Should push boulder right, player shouldn't move
        p.moveRight();
        
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        
        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }
    
    @Test
    public void pushBoulderOntoTreasure(){
        d.addEntity(p);
        d.addEntity(b);
        Treasure t = new Treasure(3, 1);
        d.addEntity(t);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        
        //Check treasure is at 3, 1
        assertEquals(t.getX(), 3);
        assertEquals(t.getY(), 1);
        
        //Should push boulder right, player shouldn't move
        p.moveRight();
        
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        
        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }
    
    @Test
    public void pushBoulderOntoDoor(){
        d.addEntity(p);
        d.addEntity(b);
        Door d1 = new Door(3, 1, 1);
        d.addEntity(d1);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        
        //Check door is at 3, 1
        assertEquals(d1.getX(), 3);
        assertEquals(d1.getY(), 1);
        
        //Should push boulder right, player shouldn't move
        p.moveRight();
        
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        
        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }
    
    @Test
    public void pushBoulderOntoKey(){
        d.addEntity(p);
        d.addEntity(b);
        Key k = new Key(3, 1, 1);
        d.addEntity(k);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        
        //Check key is at 3, 1
        assertEquals(k.getX(), 3);
        assertEquals(k.getY(), 1);
        
        //Should push boulder right, player shouldn't move
        p.moveRight();
        
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }
    
    @Test
    public void pushBoulderOntoBoulder(){
        d.addEntity(p);
        d.addEntity(b);
        Boulder b2 = new Boulder(3, 1);
        d.addEntity(b2);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        
        //Check boulder2 is at 3, 1
        assertEquals(b2.getX(), 3);
        assertEquals(b2.getY(), 1);
        
        //Should push boulder right, player shouldn't move
        p.moveRight();
        
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        
        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }
    
    @Test
    public void pushBoulderOntoPortal(){
        d.addEntity(p);
        d.addEntity(b);
        Portal portal = new Portal(3, 1, 1);
        d.addEntity(portal);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        
        //Check portal is at 3, 1
        assertEquals(portal.getX(), 3);
        assertEquals(portal.getY(), 1);
        
        //Should push boulder right, player shouldn't move
        p.moveRight();
        
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        
        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }
    
    @Test
    public void pushBoulderOntoEnemy(){
        d.addEntity(p);
        d.addEntity(b);
        Enemy enemy = new Enemy(d, 3, 1);
        d.addEntity(enemy);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        
        //Check enemy is at 3, 1
        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 1);
        
        //Should push boulder right, player shouldn't move
        p.moveRight();
        
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        
        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }
    
    @Test
    public void pushBoulderOntoSword(){
        d.addEntity(p);
        d.addEntity(b);
        Sword s = new Sword(3, 1);
        d.addEntity(s);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        
        //Check sword is at 3, 1
        assertEquals(s.getX(), 3);
        assertEquals(s.getY(), 1);
        
        //Should push boulder right, player shouldn't move
        p.moveRight();
        
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        
        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }
    
    @Test
    public void pushBoulderOntoPotion(){
        d.addEntity(p);
        d.addEntity(b);
        InvincibilityPotion potion = new InvincibilityPotion(3, 1);
        d.addEntity(potion);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        
        //Check potion is at 3, 1
        assertEquals(potion.getX(), 3);
        assertEquals(potion.getY(), 1);
        
        //Should push boulder right, player shouldn't move
        p.moveRight();
        
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        
        //Check the boulder is hasn't moved
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }

    @Test
    public void pushBoulderOntoFloorSwitch(){
        d.addEntity(p);
        d.addEntity(b);
        FloorSwitch fS = new FloorSwitch(3, 1);
        d.addEntity(fS);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    
        //Check fS is at 3, 1
        assertEquals(fS.getX(), 3);
        assertEquals(fS.getY(), 1);
    
        //Check fS is currently off
        assertFalse(fS.isTriggered());
    
        //Should push boulder right, player shouldn't move
        p.moveRight();
    
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
    
        //Check the boulder is has moved
        assertEquals(b.getX(), 3);
        assertEquals(b.getY(), 1);
    
        //Check the floor switch has been triggered
        assertTrue(fS.isTriggered());
    }
    
    @Test
    public void pushBoulderOffFloorSwitch(){
        d.addEntity(p);
        d.addEntity(b);
        FloorSwitch fS = new FloorSwitch(3, 1);
        d.addEntity(fS);
        //Check the boulder is at 2, 1
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    
        //Check fS is at 3, 1
        assertEquals(fS.getX(), 3);
        assertEquals(fS.getY(), 1);
    
        //Check fS is currently off
        assertFalse(fS.isTriggered());
    
        //Should push boulder right, player shouldn't move
        p.moveRight();
    
        //Check the player hasn't moved
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
    
        //Check the boulder is has moved
        assertEquals(b.getX(), 3);
        assertEquals(b.getY(), 1);
    
        //Check the floor switch has been triggered
        assertTrue(fS.isTriggered());
    
        //Move right to where boulder used to be
        p.moveRight();
        //Push boulder
    
        p.moveRight();
    
        //Check the floor switch is no longer triggered
        assertFalse(fS.isTriggered());
    }

    @Test
    public void playerStepOnSwitch(){
        d.addEntity(p);
        FloorSwitch fS = new FloorSwitch(2, 1);
        d.addEntity(fS);
    
        //Check fS is at 2, 1
        assertEquals(fS.getX(), 2);
        assertEquals(fS.getY(), 1);
    
        //Check fS is currently off
        assertFalse(fS.isTriggered());
    
        //Move player onto floor switch
        p.moveRight();
    
        //Check the player is on floor switch
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
    
        //Check the floor switch hasn't been triggered
        assertFalse(fS.isTriggered());
    }

    @Test
    public void playerStepOffSwitch(){
        d.addEntity(p);
        FloorSwitch fS = new FloorSwitch(2, 1);
        d.addEntity(fS);
    
        //Check fS is at 2, 1
        assertEquals(fS.getX(), 2);
        assertEquals(fS.getY(), 1);
    
        //Check fS is currently off
        assertFalse(fS.isTriggered());
    
        //Move player onto floor switch
        p.moveRight();
    
        //Check the player is on floor switch
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
    
        //Check the floor switch hasn't been triggered
        assertFalse(fS.isTriggered());

        //Move off floor switch
        p.moveRight();

        //Check floor switch still not triggered
        assertFalse(fS.isTriggered());
    }

    @Test
    public void enemyStepOnSwitch(){
        d.addEntity(p);
        FloorSwitch fS = new FloorSwitch(3, 1);
        d.addEntity(fS);
        Enemy enemy = new Enemy(d, 4, 1);
        d.addEntity(enemy);
        //Check fS is at 3, 1
        assertEquals(fS.getX(), 3);
        assertEquals(fS.getY(), 1);
    
        //Check fS is currently off
        assertFalse(fS.isTriggered());
    
        //Move player onto floor switch
        p.moveRight();
    
        //Check the enemy has moved onto floor switch
        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 1);
    
        //Check the floor switch hasn't been triggered
        assertFalse(fS.isTriggered());
    }

    @Test
    public void enemyStepOffSwitch(){
        d.addEntity(p);
        FloorSwitch fS = new FloorSwitch(3, 1);
        d.addEntity(fS);
        Enemy enemy = new Enemy(d, 4, 1);
        d.addEntity(enemy);
        //Check fS is at 3, 1
        assertEquals(fS.getX(), 3);
        assertEquals(fS.getY(), 1);
    
        //Check fS is currently off
        assertFalse(fS.isTriggered());
    
        //Move player onto floor switch
        p.moveRight();
    
        //Check the enemy has moved onto floor switch
        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 1);
    
        //Check the floor switch hasn't been triggered
        assertFalse(fS.isTriggered());

        //Move backwards to get enemy to move
        p.moveLeft();

        //Check enemy has moved off the floor switch
        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.getY(), 1);
        
        //Check floor switch still not triggered
        assertFalse(fS.isTriggered());
    }
}