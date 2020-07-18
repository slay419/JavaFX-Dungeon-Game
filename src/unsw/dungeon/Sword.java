package unsw.dungeon;

public class Sword extends Entity{
    
    private int charges;

    public Sword(int x, int y){
        super(x,y);
        setImpassible(false);
        setName("sword");
        setItem(true);
        this.charges = 5;
    }

    @Override
    public void process(Player player) {
        Inventory inventory = player.getInventory();
        inventory.add(this);
        System.out.println("Added Sword to inventory!");
    }

    public void consumeCharge(){
        this.charges = this.charges - 1;
    }

}