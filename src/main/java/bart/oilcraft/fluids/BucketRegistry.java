package bart.oilcraft.fluids;

import bart.oilcraft.items.OilCraftItemRegistry;
import bart.oilcraft.lib.handler.BucketHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class BucketRegistry {
    public static void registerBucket() {
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(ModFluids.Oil.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(OilCraftItemRegistry.oilBucket), new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.OilBlock, OilCraftItemRegistry.oilBucket);
    }
}