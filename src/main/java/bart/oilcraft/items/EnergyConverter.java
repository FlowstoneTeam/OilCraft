package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.item.Item;


/**
 * Created by Bart on 20-7-2014.
 */
public class EnergyConverter extends Item {

    public EnergyConverter(){

        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setTextureName(References.RESOURCESPREFIX + getName());
    }

}
