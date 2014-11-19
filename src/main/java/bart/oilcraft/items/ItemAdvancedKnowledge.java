package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.item.Item;

/**
 * Created by Bart on 19-11-2014.
 */
public class ItemAdvancedKnowledge extends Item {
    public ItemAdvancedKnowledge(){
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".itemadvancedknowledge");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }
    public String getName() {
        return "itemadvancedknowledge";
    }
}
