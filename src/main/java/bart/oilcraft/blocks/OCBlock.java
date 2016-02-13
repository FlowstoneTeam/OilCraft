package bart.oilcraft.blocks;


import bart.oilcraft.creativetabs.OCCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.model.ModelResourceLocation;

/**
 * Created by Bart on 12/02/2016.
 */
public class OCBlock extends Block{
    public OCBlock(Material material, MapColor mapColor) {
        super(material, mapColor);
        this.setCreativeTab(OCCreativeTabs.main);
    }

    public String blockName(int meta){
        return null;
    }

    public int[] modelMetas(){
        return new int[]{0};
    }
}
