package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.item.Item;

/**
 * Created by Bart on 30-10-2014.
 */
public class ItemEnergyDistributeUpgrade extends Item {
    public ItemEnergyDistributeUpgrade() {
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".energydistributeupgrade");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.maxStackSize = 1;
    }

    public String getName() {
        return "energydistributeupgrade";
    }


}
