package unsw.dungeon;

public class BrokenWall extends Entity {

    public BrokenWall(int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("brokenWall");
    }

    //The broken wall entity functions as a secret level starter
    @Override
    public void process(Player player) {
        player.moveToSecretLevel("secretLevel1.json");
    }
}