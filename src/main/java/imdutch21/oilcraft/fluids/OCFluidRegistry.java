package imdutch21.oilcraft.fluids;


import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Bart on 12/02/2016.
 */
public class OCFluidRegistry {
    public static Fluid OIL;
    public static Fluid FUEL;

    public static void init() {
        OIL = new OCFluid("oil").setDensity(3000);
        OIL = FluidRegistry.getFluid("oil");
        if(!FluidRegistry.getBucketFluids().contains(OIL))
            FluidRegistry.addBucketForFluid(OIL);

        FUEL = new OCFluid("fuel").setDensity(3000);
        FUEL = FluidRegistry.getFluid("fuel");
        if(!FluidRegistry.getBucketFluids().contains(FUEL))
            FluidRegistry.addBucketForFluid(FUEL);
    }
}
