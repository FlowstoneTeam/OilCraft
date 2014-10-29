package bart.oilcraft.containers;


import bart.oilcraft.tileentities.TileEntitySlipperyRemover;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

/**
 * Created by Bart on 29-10-2014.
 */
public class ContainerSlipperyRemover extends Container {
    public ContainerSlipperyRemover(EntityPlayer player, TileEntitySlipperyRemover entity) {
        createSlots(entity, player);
        bindPlayerInventory(player.inventory);
    }
    public static final ItemStack[] slot1 = new ItemStack[] {new ItemStack(Items.paper)};


    private void bindPlayerInventory(InventoryPlayer inv) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inv, i, 8 + i * 18, 142));
        }
    }


    private boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    private void createSlots(TileEntitySlipperyRemover tile, EntityPlayer player) {
        addSlotToContainer(new Slot(tile, 0, 57, 24));
        addSlotToContainer(new SlotWhitelist(tile, 1, 57, 46, slot1));
        addSlotToContainer(new SlotFurnace(player, tile, 2, 100, 36));
    }


    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.isUseableByPlayer(player);
    }
}
