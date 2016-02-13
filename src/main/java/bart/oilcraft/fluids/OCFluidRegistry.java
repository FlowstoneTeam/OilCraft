package bart.oilcraft.fluids;


import bart.oilcraft.recipes.OCMaterials;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Bart on 12/02/2016.
 */
public class OCFluidRegistry {
    public static Fluid oil;
    public static BlockFluidClassic oilBlock;

    public static void init(){
        oil = new OCFluid("oil");
        oilBlock = new OCFluidBlock(oil, OCMaterials.oil, "oil");
    }
}
