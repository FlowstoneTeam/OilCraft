package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.item.Item;

/**
 * Created by Bart on 27-10-2014.
 */
public class OilBall extends Item {
    public OilBall(){
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".OilBall");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }
    public String getName() {
        return "OilBall";
    }
}
