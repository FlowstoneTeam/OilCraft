package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.tileentities.OilCompressorEntity;
import bart.oilcraft.tileentities.OilInfuserEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
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
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(4f);
    }

    @Override
    public String getName() {
        return "OilInfuser";
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new OilInfuserEntity();
    }
}
