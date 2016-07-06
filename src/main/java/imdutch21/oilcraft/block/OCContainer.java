package imdutch21.oilcraft.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OCContainer extends OCBlock implements ITileEntityProvider {
    public OCContainer(Material material, MapColor mapColor) {
        super(material, mapColor);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        IInventory tile = (IInventory) world.getTileEntity(pos);
        if (tile != null) {
            for (int i = 0; i < tile.getSizeInventory(); i++) {
                ItemStack stack = tile.getStackInSlot(i);
                if (stack != null) {
                    if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops")) {
                        float f = 0.7F;
                        double xOffset = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                        double yOffset = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                        double zOffset = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                        EntityItem item = new EntityItem(world, pos.getX() + xOffset, pos.getY() + yOffset, pos.getZ() + zOffset, stack);
                        item.setDefaultPickupDelay();
                        world.spawnEntityInWorld(item);
                    }
                }
            }
        }
        super.breakBlock(world, pos, state);

        world.removeTileEntity(pos);
    }
}
