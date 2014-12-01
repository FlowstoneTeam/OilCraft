package bart.oilcraft.containers;

import bart.oilcraft.tileentities.TileEntityLiquidizer;
import bart.oilcraft.tileentities.TileEntityOilFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

/**
 * Created by Bart on 30-11-2014.
 */
public class ContainerLiquidizer /*extends Container*/ {
    /*public ContainerLiquidizer(EntityPlayer player, TileEntityLiquidizer entity) {
        createSlots(entity, player);
        bindPlayerInventory(player.inventory);
    }


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

    private void createSlots(TileEntityLiquidizer tile, EntityPlayer player) {
        addSlotToContainer(new Slot(tile, 3, 175, 8));
    }


    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.isUseableByPlayer(player);
    }*/
}
