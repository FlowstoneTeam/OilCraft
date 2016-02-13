package bart.oilcraft.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Bart on 12/02/2016.
 */
public class OCFluid extends Fluid {
    public OCFluid(String fluidName) {
        super(fluidName, new ResourceLocation("oilcraft:blocks/" + fluidName + "_still"), new ResourceLocation("oilcraft:blocks/" + fluidName + "_flowing"));
        this.setViscosity(600);
        FluidRegistry.registerFluid(this);
    }
}
