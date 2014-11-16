package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Bart on 30-10-2014.
 */
public class EnergyDistributeUpgrade extends Item {
    public EnergyDistributeUpgrade(){
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".energydistributeupgrade");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.maxStackSize = 1;
    }
    public String getName() {
        return "energydistributeupgrade";
    }


}
