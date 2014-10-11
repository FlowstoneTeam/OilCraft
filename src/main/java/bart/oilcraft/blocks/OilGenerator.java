package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.block.Block;

/**
 * Created by Bart on 11-10-2014.
 */
public class OilGenerator extends OilCraftBlock {
    public OilGenerator() {
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);
        this.setBlockTextureName(References.RESOURCESPREFIX + getName());
        this.setHardness(4f);
    }

    @Override
    public String getName() {
        return "CrudeOilOre";
    }


    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }
}
