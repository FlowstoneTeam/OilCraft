package bart.oilcraft.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Slot;

/**
 * Created by Bart on 28-9-2014.
 */
public class SlotBlocked extends Slot {

    public SlotBlocked(IInventory inv, int slotNum, int xPos, int yPos)
    {
        super(inv, slotNum, xPos, yPos);
    }

    @Override
    public boolean isItemValid(ItemStack item)
    {
        return false;
    }
}
