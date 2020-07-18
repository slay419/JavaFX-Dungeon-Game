package unsw.dungeon;

public class InvincibilityPotion extends Entity {

    private int charges;
    private int chargeDuration;

    public InvincibilityPotion(int x, int y) {
        super(x, y);
        setCharges(chargeDuration);
        setImpassible(false);
        setName("Invincibility Potion");
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public int getCharges() {
        return charges;
    }

    @Override
    public void process(Player player) {
        // Reset the charges and remove potion from level if the player is already invincible
        if (alreadyInvincible(player)) {
            player.pickUp(this);
            setCharges(chargeDuration);
        } else {
            Inventory inventory = player.getInventory();
            inventory.add(this);
        }
    }

    /**
     * Returns true if the player already has a potion equipped with charges
     */
    private Boolean alreadyInvincible(Player player) {
        Inventory inventory = player.getInventory();
        if (inventory.getPotion() != null) {
            return true;
        }
        return false;
    }
}