package bart.oilcraft.containers;

import bart.oilcraft.items.ModItems;
import bart.oilcraft.tileentities.OilCompressorEntity;
import bart.oilcraft.util.OilCompressorRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;


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
        addSlotToContainer(new Slot(tile, 0, 57, 36));
        addSlotToContainer(new SlotWhitelist(tile, 1, 107, 36, slot1));
        addSlotToContainer(new SlotFurnace(player, tile, 2, 152, 36));
        addSlotToContainer(new Slot(tile, 3, 175, 8));
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
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) { return null;}



}
