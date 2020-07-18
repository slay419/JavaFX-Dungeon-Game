package unsw.dungeon;

public class InvincibilityPotion extends Entity {

    private int charges;
    private int chargeDuration = 10;

    public InvincibilityPotion(int x, int y) {
        super(x, y);
        setCharges(chargeDuration);
        setImpassible(false);
        setName("Invincibility Potion");
    }

    public void setCharges(int charges) {
        this.charges = charges;
        System.out.println("this charges = " + this.charges);
        System.out.println("charges = " + charges);
    }

    public int getCharges() {
        return charges;
    }

    @Override
    public void process(Player player) {
        // Reset the charges and remove potion from level if the player is already invincible
        Inventory inventory = player.getInventory();
        inventory.add(this);
        /*
        if (alreadyInvincible(player)) {
            player.pickUp(this);
            setCharges(chargeDuration);
            System.out.println("Setting charge to: " + chargeDuration);
        } else {
            Inventory inventory = player.getInventory();
            inventory.add(this);
        }
        */
    }

    /**
     * Returns true if the player already has a potion equipped with charges
     */
    private Boolean alreadyInvincible(Player player) {
        return player.isInvincible();
    }

    public void useCharge(Player player) {
        setCharges(getCharges() - 1);
        System.out.println("Potion charge remaining: " + getCharges());
        if (getCharges() == 0) {
            Inventory inventory = player.getInventory();
            System.out.println("Removed the potion");
            inventory.removePotion();
        }
    }
}