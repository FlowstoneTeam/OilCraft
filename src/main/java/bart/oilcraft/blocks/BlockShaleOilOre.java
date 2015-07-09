package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Bart on 19-7-2014.
 */
public class BlockShaleOilOre extends Block {


    public BlockShaleOilOre() {
        super(Material.rock);
        this.setBlockName("oilcraft.shaleoilore");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);
        this.setBlockTextureName(References.RESOURCESPREFIX + "shaleoilore");
        this.setHardness(4f);
    }
}
