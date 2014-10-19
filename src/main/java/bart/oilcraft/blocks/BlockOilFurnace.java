package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.OilInfuserEntity;
import bart.oilcraft.tileentities.TileEntityOilFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by bart on 18-10-2014.
 */
public class BlockOilFurnace extends OilCraftBlock implements ITileEntityProvider {

    public BlockOilFurnace() {
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
        this.setBlockTextureName(References.RESOURCESPREFIX + getName());
        this.setHardness(4f);
    }

    @Override
    public String getName() {
        return ("blockOilFurnace");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityOilFurnace();
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
        if (entityPlayer.isSneaking()) return false;
        else
        {
            if (!world.isRemote)
            {
                entityPlayer.openGui(OilCraftMain.instance, 2, world, x, y, z);
            }
            return true;
        }
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }


}
