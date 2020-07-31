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

    //Adds sword to the players inventory if they don't already have on in inventory
    @Override
    public void process(Player player) {
        Inventory inventory = player.getInventory();
        if(!inventory.hasSword()){
            inventory.add(this);
            player.removeImage(this);
            player.updateChargesSwordUI(charges);
        }
    }

    public void consumeCharge(){
        this.charges = this.charges - 1;
    }

    public int getCharges() {
        return charges;
    }
}