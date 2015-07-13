package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.items.OilCraftItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

import static bart.oilcraft.util.OreDictionaryHelper.*;

/**
 * Created by Bart on 9-7-2015.
 */
public class IOilContainer extends Block implements ITileEntityProvider {
    public int guiID = -1;
    public boolean makesOil = false;

    public IOilContainer(Material material) {
        super(material);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }

    public boolean checkForStructure() {
        return true;
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
        if (entityPlayer.isSneaking() && !checkForStructure()) return false;
        if (world.getTileEntity(x, y, z) != null && entityPlayer.getCurrentEquippedItem() != null) {
            TileEntity te = world.getTileEntity(x, y, z);
            ItemStack equippedItem = entityPlayer.getCurrentEquippedItem();
            if (equippedItem.getItem() == getOres(OIL_BUCKET).get(0).getItem() && te instanceof IFluidHandler && !makesOil) {
                int sending = 1000;
                int filling = ((IFluidHandler) te).fill(ForgeDirection.UP, new FluidStack(getFluid(OIL), sending), false);
                if (filling == sending) {
                    entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.bucket, 1));
                    entityPlayer.inventory.consumeInventoryItem(OilCraftItemRegistry.oilBucket);
                    ((IFluidHandler) te).fill(ForgeDirection.UP, new FluidStack(getFluid(OIL), sending), true);
                    return true;
                }
            } else if (equippedItem.getItem() == Items.bucket && te instanceof IFluidHandler && makesOil) {
                int asking = 1000;
                FluidStack emptying = ((IFluidHandler) te).drain(ForgeDirection.UP, new FluidStack(getFluid(OIL), asking), false);
                if (emptying.amount == asking) {
                    entityPlayer.inventory.consumeInventoryItem(Items.bucket);
                    entityPlayer.inventory.addItemStackToInventory(new ItemStack(getOres(OIL_BUCKET).get(0).getItem(), 1));
                    ((IFluidHandler) te).drain(ForgeDirection.UP, new FluidStack(getFluid(OIL), asking), true);
                    return true;
                }
            }
        }
        if (guiID >= 0 && !world.isRemote) {
            entityPlayer.openGui(OilCraftMain.instance, guiID, world, x, y, z);
            return true;
        }
        return false;
    }
}
