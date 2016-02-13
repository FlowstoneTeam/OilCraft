package bart.oilcraft.creativetabs;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Bart on 12/02/2016.
 */
public abstract class OCCreativeTab extends CreativeTabs {
    public OCCreativeTab(String label) {
        super(label);
    }

    public void setTab(Block... blocks) {
        for( Block block : blocks ) {
            if( block != null ) {
                block.setCreativeTab(this);
            }
        }
    }

    public void setTab(Item... items) {
        for( Item item : items ) {
            if( item != null ) {
                item.setCreativeTab(this);
            }
        }
    }
}
