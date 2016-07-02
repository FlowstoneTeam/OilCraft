package bart.oilcraft.creativetab;

import bart.oilcraft.fluids.OCFluidRegistry;
import bart.oilcraft.item.OCItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

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
