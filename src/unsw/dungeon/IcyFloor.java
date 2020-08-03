package unsw.dungeon;


public class IcyFloor extends Entity {

    public IcyFloor(int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("icyFloor");
    }

    @Override
    public void process(Player player) {
        // System.out.println("found an icy floor");
        int prevX = player.getPrevX();
        int prevY = player.getPrevY();
        System.out.println("Old coords are: (" + prevX + ", " + prevY + ")");

        int iceX = getX();
        int iceY = getY();

        System.out.println("Ice coords are: (" + iceX + ", " + iceY + ")");

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

    /**
     * While the player is touching ice, it will continue to move in the direction they are going
     * @param player
     */
    private void slideRight(Player player) {
        player.moveLeft();
        player.moveLeft();     
    }

    
}