package bart.oilcraft.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;


public class OilCraftTab extends CreativeTabs {
    public OilCraftTab(int id, String modid) {
        super(id, modid);
    }

    @Override
    public Item getTabIconItem() {
        return Items.bucket;
    }
}