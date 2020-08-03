package unsw.dungeon;


public class IcyFloor extends Entity {

    public IcyFloor(int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("icyFloor");
    }

    @Override
    public void process(Player player) {
        int prevX = player.getPrevX();
        int prevY = player.getPrevY();

        int iceX = getX();
        int iceY = getY();

        // Sliding horizontally
        if (prevY == iceY) {
            if (prevX < iceX) {
                // Sliding right
                player.moveRight();
            } else if (prevX > iceX) {
                // Sliding left
                player.moveLeft();
            }
        } else if (prevX == iceX) {
            // Sliding vertically
            if (prevY < iceY) {
                // Sliding down
                player.moveDown();
            } else if (prevY > iceY) {
                // Sliding up
                player.moveUp();
            }
        }
    }
}