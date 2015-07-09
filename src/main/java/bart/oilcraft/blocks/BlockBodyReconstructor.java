package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Bart on 1-12-2014.
 */
public class BlockBodyReconstructor extends Block {

    public BlockBodyReconstructor() {
        super(Material.iron);
        this.setBlockName("oilcraft.blockbodyreconstructor");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setBlockTextureName(References.RESOURCESPREFIX + "general_machine");
    }

}
