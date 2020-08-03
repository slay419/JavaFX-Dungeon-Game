package unsw.dungeon;

public class BrokenWall extends Entity {

    public BrokenWall(int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("brokenWall");
    }

    @Override
    public void process(Player player) {
        System.out.println("found a broken wall");
        player.moveToSecretLevel("secretLevel1.json");
    }
}