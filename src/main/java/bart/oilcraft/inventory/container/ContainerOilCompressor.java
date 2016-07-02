package bart.oilcraft.inventory.container;


import bart.oilcraft.inventory.slot.SlotRestricted;
import bart.oilcraft.tileentity.TileEntityOilCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;

public class ContainerOilCompressor extends Container {

    public ContainerOilCompressor(EntityPlayer player, TileEntityOilCompressor tile) {
        addSlotToContainer(new Slot(tile, 0, 57, 36));
        addSlotToContainer(new SlotRestricted(tile, 1, 107, 36, new ItemStack(Items.BUCKET), 64));
        addSlotToContainer(new SlotFurnaceOutput(player, tile, 2, 152, 36));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = null;
        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();
            if (index > 2) {
                if (stack1.getItem() == Items.BUCKET)
                    if (!mergeItemStack(stack1, 1, 2, true))
                        return null;
                if (!mergeItemStack(stack1, 0, 1, true))
                    return null;
            }
            if (stack1.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();
            if (stack1.stackSize != stack.stackSize)
                slot.onPickupFromSlot(player, stack1);
            else
                return null;
        }
        return stack;
    }
}
