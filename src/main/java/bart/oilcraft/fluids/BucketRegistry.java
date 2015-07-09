package bart.oilcraft.fluids;

import bart.oilcraft.items.OilCraftItemRegistry;
import bart.oilcraft.lib.handler.BucketHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class BucketRegistry {
    public static void registerBucket() {
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(OilCraftFluidRegistry.oil.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(OilCraftItemRegistry.oilBucket), new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(OilCraftFluidRegistry.oilBlock, OilCraftItemRegistry.oilBucket);
    }
}