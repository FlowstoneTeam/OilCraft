package bart.oilcraft.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class OilCraftFluids extends Fluid {

    public OilCraftFluids(String name) {
        super(name);
        FluidRegistry.registerFluid(this);
    }
}