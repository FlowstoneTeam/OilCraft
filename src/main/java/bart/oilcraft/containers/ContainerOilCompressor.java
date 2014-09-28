package bart.oilcraft.containers;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.blocks.OilCompressor;
import bart.oilcraft.lib.handler.ConfigurationHandler;
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
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

import javax.security.auth.login.Configuration;

/**
 * Created by Bart on 20-7-2014.
 */
public class ContainerOilCompressor extends Container {

    public static final ItemStack[] slot1 = new ItemStack[] {new ItemStack(Items.bucket)};

    public Slot input;

    public ContainerOilCompressor(EntityPlayer player, OilCompressorEntity entity) {
        createSlots(entity, player);
        bindPlayerInventory(player.inventory);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.isUseableByPlayer(player);
    }

    private boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    private void createSlots(OilCompressorEntity tile, EntityPlayer player) {
        addSlotToContainer(new SlotWhitelist(tile, 0, 57, 36, OilCompressorRegistry.allowedItems));
        addSlotToContainer(new SlotWhitelist(tile, 1, 107, 36, slot1));
        addSlotToContainer(new Slot(tile, 2, 152, 36));
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
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
        return null;
    }



}
