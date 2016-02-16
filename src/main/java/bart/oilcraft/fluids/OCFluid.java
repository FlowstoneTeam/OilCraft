package bart.oilcraft.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class OCFluid extends Fluid {
    public OCFluid(String fluidName) {
        super(fluidName, new ResourceLocation("oilcraft:blocks/" + fluidName + "Still"), new ResourceLocation("oilcraft:blocks/" + fluidName + "Flowing"));
        this.setViscosity(600);
        FluidRegistry.registerFluid(this);
    }
}
