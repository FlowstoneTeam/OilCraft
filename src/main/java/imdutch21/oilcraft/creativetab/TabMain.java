package imdutch21.oilcraft.creativetab;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by Bart on 12/02/2016.
 */
public class TabMain extends OCCreativeTab {
    public TabMain() {
        super("oilcraft.main");
    }

    @Override
    public Item getTabIconItem() {
        return Items.BUCKET;
    }
}
