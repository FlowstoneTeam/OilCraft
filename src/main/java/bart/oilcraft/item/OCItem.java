package bart.oilcraft.item;

import bart.oilcraft.creativetab.OCCreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Bart on 14/02/2016.
 */
public class OCItem extends Item {
    public OCItem(String name){
        this.setUnlocalizedName("oilcraft." + name);
        this.setCreativeTab(OCCreativeTabs.main);
    }

    public String itemName(int meta){
        return null;
    }

    public int[] modelMetas(){
        return new int[]{0};
    }
}
