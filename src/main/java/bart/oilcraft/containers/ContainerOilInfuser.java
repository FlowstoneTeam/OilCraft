package bart.oilcraft.containers;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.blocks.OilInfuser;
import bart.oilcraft.tileentities.OilCompressorEntity;
import bart.oilcraft.tileentities.OilInfuserEntity;
import bart.oilcraft.util.OilCompressorRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Bart on 11-9-2014.
 */
public class ContainerOilInfuser extends Container {

    public ContainerOilInfuser(EntityPlayer player, OilInfuserEntity entity) {
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

    private void createSlots(OilInfuserEntity tile, EntityPlayer player) {
        addSlotToContainer(new SlotWhitelist(tile, 0, 57, 36, OilCompressorRegistry.allowedItems));
        addSlotToContainer(new Slot(tile, 1, 100, 36));
    }


        @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.isUseableByPlayer(player);
    }
}
