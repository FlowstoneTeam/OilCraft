package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import bart.oilcraft.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

/**
 * Created by Bart on 20-7-2014.
 */
public class ItemOilBucket extends ItemBucket implements ItemOredict {
    public ItemOilBucket(Block block) {
        super(block);
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".oilbucket");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }

    public String getName() {
        return "oilbucket";
    }

    @Override
    public String getOreDictName() {
        return OreDictionaryHelper.OIL_BUCKET;
    }
}
