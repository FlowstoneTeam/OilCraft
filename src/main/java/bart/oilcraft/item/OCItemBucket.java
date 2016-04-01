package bart.oilcraft.item;

import bart.oilcraft.creativetab.OCCreativeTabs;
import bart.oilcraft.util.BucketHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Bart on 14/02/2016.
 */
public class OCItemBucket extends ItemBucket {
    public String itemName = "";
    public OCItemBucket(Block containedBlock, String fluidName) {
        super(containedBlock);
        this.setUnlocalizedName("oilcraft." + fluidName + "Bucket");
        this.setCreativeTab(OCCreativeTabs.main);
        this.maxStackSize = 1;
        this.setContainerItem(Items.bucket);
        itemName = fluidName + "Bucket";

        FluidContainerRegistry.registerFluidContainer(new FluidStack(FluidRegistry.getFluid(fluidName), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(this), new ItemStack(Items.bucket));

        BucketHandler.INSTANCE.buckets.put(containedBlock, this);
    }

    public String itemName(int meta){
        return itemName;
    }

    public int[] modelMetas(){
        return new int[]{0};
    }
}
