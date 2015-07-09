package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

/**
 * Created by Bart on 16-11-2014.
 */
public class BlockOilCraftStairs extends BlockStairs {

    public BlockOilCraftStairs(Block materialBlock, String name, int meta) {
        super(materialBlock, meta);
        this.setBlockName("oilcraft" + name + "stair_" + meta);
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(4f);
        if (materialBlock instanceof BlockSpeedDecorative)
            this.slipperiness = 0.9f;
        this.getUseNeighborBrightness();
    }
}
