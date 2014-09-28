package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.OilCompressorEntity;
import bart.oilcraft.tileentities.OilInfuserEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Bart on 10-9-2014.
 */
public class OilInfuser extends OilCraftBlock implements ITileEntityProvider {


    public OilInfuser(){
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(4f);
        this.setBlockTextureName(References.RESOURCESPREFIX + "general_machine");
    }

    @Override
    public String getName() {
        return "OilInfuser";
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new OilInfuserEntity();
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
        if (entityPlayer.isSneaking()) return false;
        else
        {
            if (!world.isRemote)
            {
                entityPlayer.openGui(OilCraftMain.instance, 1, world, x, y, z);
            }
            return true;
        }
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }


}
