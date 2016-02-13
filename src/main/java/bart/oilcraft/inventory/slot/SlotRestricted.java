package bart.oilcraft.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Bart on 12/02/2016.
 */
public class SlotRestricted extends Slot {
    ItemStack item;
    int maxItems;

    public SlotRestricted(IInventory inventory, int slotIndex, int x, int y, ItemStack item, int maxItems) {
        super(inventory, slotIndex, x, y);
        this.item = item;
        this.maxItems = maxItems;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == item.getItem() && stack.getItemDamage() == item.getItemDamage();
    }

    public int getSlotStackLimit() {
        return maxItems;
    }
}
