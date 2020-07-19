package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.Player;

public class ExitTest {
    @Test
    public void playerNotAtExit() {
        Dungeon d = new Dungeon(10, 10);
        Player player = new Player(d, 1, 1);
        Exit exit = new Exit(10 ,10);
        d.addEntity(player);
        d.addEntity(exit);
        assertFalse(exit.reachedExit(player));
    }

    @Test
    public void playerAtExit() {
        Dungeon d = new Dungeon(10, 10);
        Player player = new Player(d, 10, 10);
        Exit exit = new Exit(10 ,10);
        d.addEntity(player);
        d.addEntity(exit);
        assertTrue(exit.reachedExit(player));
    }

    @Test
    public void playerMoveToExit() {
        Dungeon d = new Dungeon(10, 10);
        Player player = new Player(d, 10, 9);
        Exit exit = new Exit(10 ,10);
        d.addEntity(player);
        d.addEntity(exit);
        assertFalse(exit.reachedExit(player));

        player.moveDown();
        assertTrue(exit.reachedExit(player));
    }

    
}