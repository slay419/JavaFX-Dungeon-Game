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

    private void setCharges(int charges) {
        this.charges = charges;
    }

    public int getCharges() {
        return charges;
    }

    // Reset the charges and remove potion from level if the player is already invincible
    @Override
    public void process(Player player) {
        Inventory inventory = player.getInventory();
        inventory.add(this);
        player.removeImage(this);
        player.updateChargesPotionUI(charges);
    }
    
    /**
     * Decrements the charge of the potion, removing the potion if there are no more charges
     * @param player
     */
    public void useCharge(Player player) {
        setCharges(getCharges() - 1);
        player.updateChargesPotionUI(charges);
        if (getCharges() == 0) {
            Inventory inventory = player.getInventory();
            inventory.removePotion();
        }
    }
}