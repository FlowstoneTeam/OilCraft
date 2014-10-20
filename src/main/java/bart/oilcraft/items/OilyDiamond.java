package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.item.Item;

/**
 * Created by bart on 20-10-2014.
 */
public class OilyDiamond extends Item {
    public OilyDiamond(){
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".OilyDiamond");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }
    public String getName() {
        return "OilyDiamond";
    }
}
