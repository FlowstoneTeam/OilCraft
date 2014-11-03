package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import javax.tools.Tool;

/**
 * Created by Bart on 19-7-2014.
 */
public class CrudeOilOre extends OilCraftBlock {


    public CrudeOilOre() {
     this.setBlockName(getName());
     this.setCreativeTab(OilCraftMain.getCreativeTab());
     this.setStepSound(Block.soundTypeStone);
     this.setBlockTextureName(References.RESOURCESPREFIX + getName());
     this.setHardness(4f);
    }

    @Override
    public String getName() {
        return "crudeoilore";
    }
}
