package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

/**
 * Created by Bart on 16-11-2014.
 */
public class OilCraftStairs extends BlockStairs {

    public OilCraftStairs(Block materialBlock, int meta) {
        super(materialBlock, meta);
        this.setBlockName(materialBlock.getUnlocalizedName() + "stair" + meta);
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(4f);
        this.slipperiness = Slippery(materialBlock);
        this.getUseNeighborBrightness();
    }

    public float Slippery(Block block){
        if (block instanceof BlockSpeedDecorative){
            return 0.80F;
        }else return 0.6F;
    }
}
