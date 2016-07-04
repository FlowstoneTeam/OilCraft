package imdutch21.oilcraft.fluids;


import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Bart on 12/02/2016.
 */
public class OCFluidRegistry {
    public static Fluid OIL;

    public static void init() {
        OIL = new OCFluid("oil").setDensity(3000);
        OIL = FluidRegistry.getFluid("oil");
        if(!FluidRegistry.getBucketFluids().contains(OIL))
            FluidRegistry.addBucketForFluid(OIL);
    }
}
