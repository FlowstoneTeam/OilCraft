package bart.oilcraft.fluids;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class OCFluid extends Fluid {
    public OCFluid(String fluidName) {
        super(fluidName, new ResourceLocation("oilcraft:blocks/" + fluidName + "Still"), new ResourceLocation("oilcraft:blocks/" + fluidName + "Flowing"));
        this.setViscosity(600);
        FluidRegistry.registerFluid(this);
    }
}
