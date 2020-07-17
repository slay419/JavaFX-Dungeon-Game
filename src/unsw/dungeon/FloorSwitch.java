package unsw.dungeon;

public class FloorSwitch extends Entity {
    
    private boolean triggered;

    public FloorSwitch(int x, int y){
        super(x, y);
        setImpassible(false);
        setName("floorSwitch");
        this.triggered = false;
        
    }

    @Override
    public void process(Player player){
        if (isTriggered()){
            setTriggered(false);
        }
        else{
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