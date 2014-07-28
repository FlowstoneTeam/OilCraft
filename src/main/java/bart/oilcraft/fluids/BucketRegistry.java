package bart.oilcraft.fluids;

import bart.oilcraft.items.ModItems;
import bart.oilcraft.lib.handler.BucketHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class BucketRegistry {
    public static void registerBucket() {
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(ModFluids.Oil.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(ModItems.OilBucket), new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.OilBlock, ModItems.OilBucket);
    }
}