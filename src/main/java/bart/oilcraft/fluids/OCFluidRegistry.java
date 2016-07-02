package bart.oilcraft.fluids;


import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Bart on 12/02/2016.
 */
public class OCFluidRegistry {
    public static Fluid OIL;

    public static void init() {
        OIL = new OCFluid("oil");
        OIL = FluidRegistry.getFluid("oil");
        if(!FluidRegistry.getBucketFluids().contains(OIL))
            FluidRegistry.addBucketForFluid(OIL);
    }
}
