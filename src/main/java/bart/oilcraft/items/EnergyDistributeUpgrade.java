package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.item.Item;

/**
 * Created by Bart on 30-10-2014.
 */
public class EnergyDistributeUpgrade extends Item {
    public EnergyDistributeUpgrade(){
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".EnergyDistributeUpgrade");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.maxStackSize = 1;
    }
    public String getName() {
        return "EnergyDistributeUpgrade";
    }
}
