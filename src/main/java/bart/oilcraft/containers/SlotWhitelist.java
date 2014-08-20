package bart.oilcraft.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Bart on 20-8-2014.
 */
public class SlotWhitelist extends Slot {

    ItemStack[] allowedItems;

    public SlotWhitelist(IInventory inv, int slotNum, int xPos, int yPos, ItemStack[] items)
    {
        super(inv, slotNum, xPos, yPos);
        allowedItems = items;
    }

    @Override
    public boolean isItemValid(ItemStack item)
    {
        return arrayContains(allowedItems, item);
    }

    public static boolean arrayContains(ItemStack[] array, ItemStack item)
    {
        for (int i = 0; i < array.length; i++)
            if (array[i].getItem() == item.getItem() && array[i].getItemDamage() == item.getItemDamage()) return true;

        return false;
    }
}
