package unsw.dungeon;

public class FloorSwitch extends Entity {
    
    private boolean triggered;

    public FloorSwitch(int x, int y){
        super(x, y);
        setImpassible(false);
        setName("floorSwitch");
        this.triggered = false;
        
    }

    public void processSwitch(Player player){
        if (isTriggered()){
            System.out.println("Turning off switch");
            setTriggered(false);
        }
        else{
            System.out.println("Turning on switch");
            setTriggered(true);
        }
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }
}