package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity {
    
    public Boulder(int x, int y){
        super(x, y);
        setImpassible(true);
        setName("boulder");
    }

    @Override
    public void process(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        int futureX = getBoulderFuturePos(playerX, getX());
        int futureY = getBoulderFuturePos(playerY, getY());
        

        //Check if currently on a Switch
        Entity currentSwitch = checkSwitchPos(getX(), getY(), player);
        Entity futureSwitch = checkSwitchPos(futureX, futureY, player);

        ArrayList<Entity> entityList = player.getEntityList(futureX, futureY);
        //Case 1, there's nothing
        if(entityList == null){
            moveBoulder(futureX, futureY);
            processSwitch(currentSwitch);
        }
        //Case 2, there's a floorswitch and nothing on it
        else if(futureSwitch != null && checkSwitchEmpty(futureX, futureY, player)){
            moveBoulder(futureX, futureY);
            processSwitch(currentSwitch);
            processSwitch(futureSwitch);
        }
        //Case 3, there's a floorswitch with a boulder on it in which nothing would happen
    }

    /**
     * Calculates the future x/y position of a pushed boulder
     * @param playerPos The player's current x/y position
     * @param boulderPos The boulder's current x/y position
     * @return
     */
    private int getBoulderFuturePos(int playerPos, int boulderPos){
        int futurePos = 0;
        for (int direction = -1; direction <= 1; direction++){
            if(playerPos + direction == boulderPos){
                futurePos = boulderPos + direction;
                break;
            }
        }
        return futurePos;
    }

    /**
     * Checks if there is a switch at a given position
     * @param x The X position of the switch being checked
     * @param y The Y position of the switch being checked
     * @param player The player character references the dungeon's entity list
     * @return
     */
    private Entity checkSwitchPos(int x, int y, Player player){
        ArrayList<Entity> entityList = player.getEntityList(x, y);
        if (entityList == null) {
            return null;
        }
        Entity entity = checkEntityList(entityList, "floorSwitch");
        return entity;
    }

    /**
     * Checks if there is another Entity on a switch
     * @param x The X position of the switch being checked
     * @param y The Y position of the switch being checked
     * @param player The player character references the dungeon's entity list
     * @return
     */
    private Boolean checkSwitchEmpty(int x, int y, Player player){
        ArrayList<Entity> entityList = player.getEntityList(x, y);
        if(entityList.size() > 1){
            return false;
        }
        return true;
    }

    /**
     * Communicates with a floorSwitch, calling processSwitch
     * @param floorSwitch The floorswitch the player is pushing the boulder onto
     * @param player The player pushing the boulder
     */
    private void processSwitch(Entity floorSwitch){
        if(floorSwitch != null){
            ((FloorSwitch) floorSwitch).processSwitch();
        }
    }

    /**
     * Moves a boulder to a certain position
     * @param x x position to move the boulder to
     * @param y y position to move the boulder to
     */
    private void moveBoulder(int x, int y){
        setXPos(x);
        setYPos(y);
    }
    
}