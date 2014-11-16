package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.block.Block;

/**
 * Created by Bart on 14-11-2014.
 */
public class BlockOilyStoneBrick extends OilCraftBlock {

    public BlockOilyStoneBrick(){
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);
        this.slipperiness = 1.00F;
        this.setBlockTextureName(References.RESOURCESPREFIX + getName());
    }

    @Override
    public String getName() {
        return "oilystonebrick";
    }
}
