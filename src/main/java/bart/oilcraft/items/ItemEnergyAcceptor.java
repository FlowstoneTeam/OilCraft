package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.item.Item;

/**
 * Created by Bart on 12-10-2014.
 */
public class ItemEnergyAcceptor extends Item {
    public ItemEnergyAcceptor() {
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".energyacceptor");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }

    public String getName() {
        return "energyacceptor";
    }
}
