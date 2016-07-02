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
        FluidRegistry.addBucketForFluid(OIL);
        System.out.println("null: " + OIL == null + ", registered: " + !FluidRegistry.isFluidRegistered(OIL) + ", contains: " + FluidRegistry.getBucketFluids().contains(OIL));
    }
}
