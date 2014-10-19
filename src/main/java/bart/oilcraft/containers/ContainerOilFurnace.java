package bart.oilcraft.containers;

import bart.oilcraft.tileentities.OilInfuserEntity;
import bart.oilcraft.tileentities.TileEntityOilFurnace;
import bart.oilcraft.util.OilCompressorRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by bart on 18-10-2014.
 */
public class ContainerOilFurnace extends Container {

    public ContainerOilFurnace(EntityPlayer player, TileEntityOilFurnace entity) {
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

    private void createSlots(TileEntityOilFurnace tile, EntityPlayer player) {
        addSlotToContainer(new SlotWhitelist(tile, 0, 57, 36, OilCompressorRegistry.allowedItems));
        addSlotToContainer(new Slot(tile, 1, 100, 36));
    }


    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.isUseableByPlayer(player);
    }
}
