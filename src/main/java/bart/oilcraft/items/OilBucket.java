package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

/**
 * Created by Bart on 20-7-2014.
 */
public class OilBucket extends ItemBucket {
    public OilBucket(Block block) {
        super(block);
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".OilBucket");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        
    }

    public String getName() {
     return "OilBucket";
    }
}
