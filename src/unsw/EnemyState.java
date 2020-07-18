package unsw;

import unsw.dungeon.Player;

public interface EnemyState {


    // Different states expected: Default & Escape

    void move(Player player);

	void interact();



}