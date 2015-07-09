package bart.oilcraft.containers;

import bart.oilcraft.tileentities.TileEntityOilFurnace;
import bart.oilcraft.tileentities.TileEntityOilRefinery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

/**
 * Created by Bart on 9-7-2015.
 */
public class ContainerOilRefinery extends Container {
    public ContainerOilRefinery(EntityPlayer player, TileEntityOilRefinery entity) {
        createSlots(entity, player);
        bindPlayerInventory(player.inventory);
    }


    private void bindPlayerInventory(InventoryPlayer inv) {
    }


    private boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    private void createSlots(TileEntityOilRefinery tile, EntityPlayer player) {
    }


    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.isUseableByPlayer(player);
    }
}
